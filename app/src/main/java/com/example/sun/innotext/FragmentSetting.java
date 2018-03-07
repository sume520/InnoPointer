package com.example.sun.innotext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentSetting extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener{

    private List<SettingItem> settingItemList=new ArrayList<SettingItem>();
    private Button username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_setting, container, false);
        initSettings();
        SettingAdapter adapter=new SettingAdapter
                (getActivity(),settingItemList);
        ListView listView=view.findViewById(R.id.listview);
        //注册设置列表点技监控
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        //注册用户名点击监控
        username=view.findViewById(R.id.username);
        username.setOnClickListener(this);

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

    @Override
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.username:
                Intent intent=new Intent(getActivity(),PersonActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"clicked user name",Toast.LENGTH_SHORT).show();
        }
    }
}
