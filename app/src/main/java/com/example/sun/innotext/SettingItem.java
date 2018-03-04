package com.example.sun.innotext;

/**
 * Created by sun on 2018/2/24.
 */

public class SettingItem {
    private int imageId;
    private String settingName;

    public SettingItem(String name, int id){
        imageId=id;
        settingName=name;
    }

    public int getImageId(){
        return imageId;
    }

    public String getSettingName(){
        return settingName;
    }
}
