package com.example.sun.innotext.user;

import android.media.Image;

/**
 * Created by sun on 2018/3/19.
 */

public class UserProfile {
    private int ID;
    private Image image;
    private String userName;
    private String nickName;
    private int deviceID;

    public int getID() {
        return ID;
    }

    public Image getImage() {
        return image;
    }

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }
}
