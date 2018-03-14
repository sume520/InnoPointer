package com.example.sun.innotext.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.sun.innotext.expansion.ExpansionActivity;
import com.example.sun.innotext.login.LoginActivity;
import com.example.sun.innotext.userdata.PersonActivity;
import com.example.sun.innotext.R;

import static android.content.ContentValues.TAG;

public class FragmentSetting extends Fragment implements View.OnClickListener{

    private RelativeLayout relativeLayout;
    private Button bt_login_logout;
    private Button bt_fingerprint;
    private Button bt_log;
    private LinearLayout ll_expand;
    private Button btn_expension;

    public static boolean isLogin=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_setting, container, false);

        setButtonListener(view);
        return view;
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
        ll_expand=view.findViewById(R.id.ll_expansion);
        ll_expand.setOnClickListener(this);
        btn_expension=view.findViewById(R.id.btn_expansion);
        btn_expension.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.user_layout:
                intent=new Intent(getContext(),PersonActivity.class);
                startActivity(intent);
                break;
            case R.id.login_logout:
                Log.d(TAG, "onClick: you click login button");
                intent=new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.fingerprint:
                Log.d(TAG, "onClick: you click fingerprint button");
                break;
            case R.id.btn_expansion:
                Log.d(TAG, "onClick: you click expansin button");
                intent=new Intent(getContext(), ExpansionActivity.class);
                startActivity(intent);
                break;
            default:
                Log.d(TAG, "onClick: you click nothing");
        }
    }
}
