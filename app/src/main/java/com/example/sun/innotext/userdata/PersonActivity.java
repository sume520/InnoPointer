package com.example.sun.innotext.userdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sun.innotext.R;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_modify_infor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Init();
    }

    private void Init(){
        bt_modify_infor=findViewById(R.id.bt_modify_information);
        bt_modify_infor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()){
            case R.id.bt_modify_information:
                intent=new Intent(PersonActivity.this,User_Infor_Activity.class);
                startActivity(intent);
        }
    }

}
