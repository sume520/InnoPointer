package com.example.sun.innotext;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.baidu.mapapi.SDKInitializer;
import com.example.sun.innotext.fragments.FragmentMap;
import com.example.sun.innotext.dbmanger.DBManager;
import com.example.sun.innotext.fragments.FragmentHome;
import com.example.sun.innotext.fragments.FragmentSetting;
import com.example.sun.innotext.socketmanger.SocketManager;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {

    //碎片
    private FragmentHome fragmentHome;
    private FragmentMap fragmentMap;
    private FragmentSetting fragmentSetting;
    private FragmentManager fragmentManager;
    private Fragment[] fragments;
    private int lastShowFragment=0;

    //套接字
    private SocketManager socketManager;

    private static final String TAG = "MainActivity";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Home");
                    if(lastShowFragment!=0){
                        switchFrament(lastShowFragment,0);
                        lastShowFragment=0;
                    }
                    return true;
                case R.id.navigation_map:
                    setTitle("Map");
                    if(lastShowFragment!=1){
                        switchFrament(lastShowFragment,1);
                        lastShowFragment=1;
                    }
                    return true;
                case R.id.navigation_setting:
                    setTitle("Setting");
                    if(lastShowFragment!=2){
                        switchFrament(lastShowFragment,2);
                        lastShowFragment=2;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        fragmentManager=getSupportFragmentManager();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //初始化碎片信息
        initFragments();

        //连接服务器
        socketManager=SocketManager.createInstance();
        socketManager.connect();
        //socketManager.getData();

        //测试数据库
        dbTest();
    }

    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fragment, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    //初始化碎片
    private void initFragments() {
        //实例化碎片
        fragmentHome=new FragmentHome();
        fragmentMap=new FragmentMap();
        fragmentSetting=new FragmentSetting();

        fragments = new Fragment[]{fragmentHome,fragmentMap,fragmentSetting};
        //设置默认显示碎片
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment,fragmentHome)
                .show(fragmentHome)
                .commit();

    }

    //获取实例化的socketManager
    public SocketManager getSocketManager(){
        return socketManager;
    }
    @Override
    protected void onDestroy() {
        socketManager.closeSocket();
        super.onDestroy();
    }

    private void dbTest(){
        Log.d(TAG, "dbTest: 正在测试数据库");
        DBManager dbManager=DBManager.createInstance();
        dbManager.connectDB();
        ResultSet rs;
        rs=dbManager.executeQuery("select * from student");
        try {
            while (rs.next()){
                Log.d(TAG, "onCreate: id "+rs.getInt
                        (1)+" username "+
                        rs.getString(2)+
                        " password "+rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "dbTest: 测试数据库完成");
    }
}
