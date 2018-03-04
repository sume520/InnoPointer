package com.example.sun.innotext;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.net.Socket;

public class FragmentHome extends Fragment implements View.OnClickListener {
    private Button rmSwitch;
    private Button lock;
    private Button light;
    private Button warn;
    private Button whistle;
    private Button resist;
    private Activity activity;
    private SocketManager socketManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        initButton(view);
        return view;
    }

    private void initButton(View view){
        rmSwitch=view.findViewById(R.id.remote_switch);
        rmSwitch.setOnClickListener(this);
        lock=view.findViewById(R.id.lock);
        lock.setOnClickListener(this);
        light=view.findViewById(R.id.light);
        light.setOnClickListener(this);
        warn=view.findViewById(R.id.warning);
        warn.setOnClickListener(this);
        whistle=view.findViewById(R.id.whistle);
        whistle.setOnClickListener(this);
        resist=view.findViewById(R.id.resist);
        resist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.remote_switch:
                socketManager.sendCommand("开启智能车锁");
                //showCommand("开启智能车锁");
                break;
            case R.id.lock:
                socketManager.sendCommand("上锁");
                //showCommand("上锁");
                break;
            case R.id.light:
                socketManager.sendCommand("打开车灯");
                //showCommand("打开车灯");
                break;
            case R.id.warning:
                socketManager.sendCommand("开启警告");
                //showCommand("开启警告");
                break;
            case R.id.resist:
                socketManager.sendCommand("开启自动抗拒");
                //showCommand("开启自动抗拒");
                break;
            case R.id.whistle:
                socketManager.sendCommand("开启鸣笛");
                //showCommand("开启鸣笛");
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
        socketManager=((MainActivity)activity).getSocketManager();
    }

    private void showCommand(String cmd){
        Socket socket=socketManager.getSocket();
        if(socket.isConnected()&&socket!=null);
          Toast.makeText(activity,cmd+"成功",Toast.LENGTH_SHORT).show();
    }
}
