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
import android.widget.TextView;

import com.example.sun.innotext.expansion.ExpansionActivity;
import com.example.sun.innotext.login.LoginActivity;
import com.example.sun.innotext.userdata.PersonActivity;
import com.example.sun.innotext.R;

import static android.content.ContentValues.TAG;

public class FragmentSetting extends Fragment implements View.OnClickListener{

    private RelativeLayout relativeLayout;
    private Button bt_login_logout;
    private LinearLayout ll_fingerprint;
    private LinearLayout ll_expansion;
    private LinearLayout ll_log;

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
        ll_fingerprint=view.findViewById(R.id.ll_fingerprint);
        ll_fingerprint.setOnClickListener(this);
        ll_expansion=view.findViewById(R.id.ll_expansion);
        ll_expansion.setOnClickListener(this);
        ll_log=view.findViewById(R.id.ll_log);
        ll_log.setOnClickListener(this);
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
            case R.id.ll_fingerprint:
                Log.d(TAG, "onClick: you click fingerprint");
                break;
            case R.id.ll_expansion:
                Log.d(TAG, "onClick: you click expansin");
                intent=new Intent(getContext(), ExpansionActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_log:
                Log.d(TAG, "onClick: you click log");
                break;
            default:
                Log.d(TAG, "onClick: you click nothing");
        }
    }
}
