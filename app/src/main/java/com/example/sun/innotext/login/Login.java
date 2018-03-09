package com.example.sun.innotext.login;

import android.util.Log;

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
    private String password;
    private String getPassword;

    public Login(String username,String password){
        init();
        this.username=username;
        this.password=password;
    }

    private void init(){
        dbManager= DBManager.createInstance();
    }

    private boolean checkEmpty(){
       return false;
    }

    private boolean checkOverLength(){
        if(username.length()>USERNAME_SIZE)
            return false;
        else
            return true;
    }

    private LoginCode attemptToLogin(){
        if(checkOverLength()) {
            return
                    LoginCode.uernameOverLength;
        }else if(isUsernameExit()){
            return
                    LoginCode.usernameUnexistence;
        }else if(isPasswordTrue()){
            return
                    LoginCode.passwordMistake;
        }
        else {
            return LoginCode.loginSucceed;
        }
    }

    private boolean isUsernameExit(){
        String sql;
        ResultSet rs=null;
        sql="select password where username="+username;
        rs=dbManager.executeQuery(sql);
        try {
            while(rs.next()){
                getPassword=rs.getString(3);
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private boolean isPasswordTrue(){
        if(password!=getPassword)
            return false;
        else return true;
    }
}
