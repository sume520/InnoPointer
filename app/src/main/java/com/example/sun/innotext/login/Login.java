package com.example.sun.innotext.login;

import android.util.Log;
import android.widget.Toast;

import com.example.sun.innotext.MainActivity;
import com.example.sun.innotext.dbmanger.DBManager;
import com.example.sun.innotext.login.login_code.LoginCode;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018/3/9.
 */

/*
* login successful      100
*
* error code
* username overlength   101
* username inexistence  102
* password mistake      103
*
*
 */

public class Login {
    //用户名和密码长度
    private final int USERNAME_SIZE=10;
    private final int PASSWORD_SIZE=18;

    private DBManager dbManager;
    private String username;
    private String getPassword;//从数据库获取的密码
    private String password;

    private static final String TAG = "Login";

    //在构造函数传入账号密码
    public Login(String username,String password){
        init();
        this.username=username;
        this.password=password;
    }

    private void init(){
        dbManager= DBManager.createInstance();
    }


    private boolean checkUername(){
        if(username.length()>USERNAME_SIZE | username.length()==0)
            return true;
        if(username!="" && username.length()>0 && username.length()<=USERNAME_SIZE)
            return false;
        return true;
    }

    //尝试登陆，并返回登陆结果
    public LoginCode attemptToLogin(){
        if(checkUername()) {
            //Toast.makeText(new MainActivity(),"用户名错误",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "attemptToLogin: 用户名错误");
            Log.d(TAG, "attemptToLogin: 用户名为"+username);
            return
                    LoginCode.uernameError;
        }
        if(isUsernameExit()){
            //Toast.makeText(new MainActivity(),"用户名不存在",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "attemptToLogin: 用户不存在");
            return
                    LoginCode.usernameUnexistence;
        }
        if(isPasswordTrue()){
            //Toast.makeText(new MainActivity(),"密码错误",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "attemptToLogin: 密码错误");
            return
                    LoginCode.passwordMistake;
        }
        Log.d(TAG, "attemptToLogin: 登陆成功");
        return LoginCode.loginSucceed;
    }

    //判断用户名是否存在
    private boolean isUsernameExit(){
        String sql;
        ResultSet rs=null;
        sql="select password where username = "+username;
        rs=dbManager.executeQuery(sql);
        if(rs!=null) {
            try {
                getPassword=rs.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //判断密码是否正确
    private boolean isPasswordTrue(){
        if(password.equals(getPassword))
            return true;
        return false;
    }
}
