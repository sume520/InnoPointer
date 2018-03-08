package com.example.sun.innotext.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sun.innotext.MainActivity;
import com.example.sun.innotext.R;
import com.example.sun.innotext.socketmanger.SocketManager;

import java.net.Socket;

public class FragmentHome extends Fragment implements View.OnClickListener {
    private Button rmSwitch;
    private Button lock;
    private Button light;
    private Button warn;
    private Button whistle;
    private Button resist;
    private SocketManager socketManager;

    private static final String TAG = "FragmentHome";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        initButton(view);
        return view;
    }

    //初始化并注册按钮
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
        socketManager=SocketManager.createInstance();
        String cmd=null;
        boolean rs=false;
        switch (v.getId()){
            case R.id.remote_switch:
                cmd="开启智能车锁";
                rs=socketManager.sendCommand(cmd);
                showResult(rs,cmd);
                break;
            case R.id.lock:
                cmd="上锁";
                rs=socketManager.sendCommand(cmd);
                showResult(rs,cmd);
                break;
            case R.id.light:
                cmd="开启车灯";
                rs=socketManager.sendCommand(cmd);
                showResult(rs,cmd);
                break;
            case R.id.warning:
                cmd="开启警告";
                rs=socketManager.sendCommand(cmd);
                showResult(rs,cmd);
                break;
            case R.id.resist:
                cmd="开启自动抗拒";
                rs=socketManager.sendCommand(cmd);
                showResult(rs,cmd);
                break;
            case R.id.whistle:
                cmd="开启鸣笛";
                rs=socketManager.sendCommand(cmd);
                showResult(rs,cmd);
                break;
        }
    }

    private void showResult(boolean rs,String cmd){
        MainActivity activity=(MainActivity)getActivity();
        if(rs){
            Toast.makeText(activity,"发送指令"+cmd+"成功",Toast.LENGTH_SHORT)
                    .show();
            Log.d(TAG, "showResult: rs=true");
        }else {
            Toast.makeText(activity,"发送指令"+cmd+"失败",Toast.LENGTH_SHORT)
                    .show();
            Log.d(TAG, "showResult: rs=false");
        }
    }

}
