package com.example.sun.innotext;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by sun on 2018/2/26.
 */

public class SocketManager {

    private Socket socket;
    public DataOutputStream out;
    public DataInputStream in;
    private Context context;
    private ExecutorService executorService;
    private Future<Boolean> future;

    private static SocketManager socketManager;
    private static String host;
    private static int port;

    public SocketManager(){
    }

    public static SocketManager createInstance(){
        if(socketManager==null){
            host="120.78.159.172";
            port=8000;
            socketManager=new SocketManager();
        }
        return socketManager;
    }


    public void connect(){
        executorService= Executors.newCachedThreadPool();
        FutureTask<Boolean> futureTask=new FutureTask<Boolean>(new SocketConnect());
        //executorService.submit(futureTask);
        checkConnect(futureTask);
    }

    public void checkConnect(final FutureTask<Boolean> futureTask){
        new Thread(new Runnable(){
            public void run(){
                while(true){
                    if(socket==null||socket.isClosed()) {
                        Log.d("SocketManger","正在重连...");
                        executorService.submit(futureTask);
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public boolean sendCommand(final String command){
        final String cmd=command;
        final boolean[] judge = {false};
        if(socket!=null) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        out.writeUTF(command);
                        out.flush();
                        judge[0] =true;
                        Log.d("SocketManger", "发送指令" + cmd + "成功");
                    } catch (IOException e) {
                        e.printStackTrace();
                        judge[0] =false;
                        Log.d("SocketManger", "发送指令" + cmd + "失败");
                    }
                }
            });
            thread.start();
        }else{
            Log.d("SocketManager","服务器连接已断开");
            judge[0]=false;
            //Toast.makeText(context,"未连接服务器",Toast.LENGTH_SHORT).show();
        }
        return judge[0];
    }

    public void getData(){
        new Thread(new Runnable(){
            public void run(){
                String msg;
                while(true){
                    try {
                        msg=in.readUTF();
                        Log.d("SocketManager",msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public Socket getSocket(){
        if(socket!=null)
            return socket;
        else return null;
    }

    public void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class SocketConnect implements Callable<Boolean>{

        @Override
        public Boolean call() throws Exception {
            while(true) {
                try {
                    socket = new Socket(host, port);
                    out = new DataOutputStream(socket.getOutputStream());
                    in = new DataInputStream(socket.getInputStream());
                    Log.d("SocketManger", "连接服务器成功");
                    Toast.makeText(context,"连接服务器成功",Toast.LENGTH_SHORT).show();
                    //return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("SocketManger", "连接服务器失败");
                    Thread.sleep(2000);
                }
            }
        }
    }
}
