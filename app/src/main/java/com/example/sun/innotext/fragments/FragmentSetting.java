package com.example.sun.innotext.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sun.innotext.LoginActivity;
import com.example.sun.innotext.PersonActivity;
import com.example.sun.innotext.R;
import com.example.sun.innotext.SettingAdapter;
import com.example.sun.innotext.SettingItem;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FragmentSetting extends Fragment implements View.OnClickListener{

    private List<SettingItem> settingItemList=new ArrayList<SettingItem>();
    private RelativeLayout relativeLayout;
    private Button bt_login_logout;
    private Button bt_fingerprint;
    private Button bt_log;

    public static boolean isLogin=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_setting, container, false);
        //initSettings();
        SettingAdapter adapter=new SettingAdapter
                (getActivity(),settingItemList);
        //ListView listView=view.findViewById(R.id.listview);
        //注册设置列表点技监控
        //listView.setOnItemClickListener(this);
        //listView.setAdapter(adapter);
        //注册用户名点击监控
        setButtonListener(view);
        return view;
    }

    private void initSettings(){
        SettingItem log=new SettingItem("log",R.drawable.ic_event_note_black_24dp);
        settingItemList.add(log);
        SettingItem fingerPrint=new SettingItem("fingerprint",R.drawable.ic_fingerprint_black_24dp);
        settingItemList.add(fingerPrint);
        SettingItem signOut=new SettingItem("logout",R.drawable.ic_login_out_black_24dp);
        settingItemList.add(signOut);
    }

    private void setButtonListener(View view){
        relativeLayout=view.findViewById(R.id.user_layout);
        relativeLayout.setOnClickListener(this);
        bt_login_logout=view.findViewById(R.id.login_logout);
        bt_login_logout.setOnClickListener(this);
        bt_fingerprint=view.findViewById(R.id.fingerprint);
        bt_fingerprint.setOnClickListener(this);
        bt_log=view.findViewById(R.id.log);
        bt_log.setOnClickListener(this);
    }

    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SettingItem item=settingItemList.get(position);

        switch (item.getSettingName()){
            case "log":
                Toast.makeText(getActivity(),item.getSettingName(),Toast.LENGTH_SHORT).show();
                break;
            case "fingerprint":
                Toast.makeText(getActivity(),item.getSettingName(),Toast.LENGTH_SHORT).show();
                break;
            case "logout":
                Toast.makeText(getActivity(),item.getSettingName(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
        }
    }*/

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.user_layout:
                intent=new Intent(getActivity(),PersonActivity.class);
                startActivity(intent);
                break;
            case R.id.login_logout:
                Log.d(TAG, "onClick: you click login button");
                intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.fingerprint:
                Log.d(TAG, "onClick: you click fingerprint button");
                break;
            default:
                Log.d(TAG, "onClick: you click nothing");
        }
    }
}
