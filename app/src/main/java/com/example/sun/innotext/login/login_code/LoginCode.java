package com.example.sun.innotext.login.login_code;

/**
 * Created by Administrator on 2018/3/9.
 */

public enum LoginCode {
    loginSucceed(100),
    uernameOverLength(101),
    usernameUnexistence(102),
    passwordMistake(103);

    private int value=0;

    private LoginCode(int value){
        this.value=value;
    }

    public static LoginCode valueOf(int value){
        switch (value){
            case 100:
                return loginSucceed;
            case 101:
                return uernameOverLength;
            case 102:
                return usernameUnexistence;
            case 103:
                return passwordMistake;
            default:
                return null;
        }
    }

    public int getValue(){
        return this.value;
    }
}
