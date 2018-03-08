package com.example.sun.innotext.dbmanger;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by sun on 2018/3/8.
 */

public class DBManager {
    //数据库连接常量
    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String USER="root";
    public static final String PASS="root2018";
    public static final String URL="jdbc:mysql://120.78.159.172:3306/test";

    private ResultSet rs = null;

    // 静态成员，支持单态模式
    private static DBManager dbManger=null;
    private Connection conn = null;
    private Statement stmt = null;

    //线程缓冲池
    public static ExecutorService executorService= Executors.newSingleThreadExecutor();
    FutureTask<Object> futureTask;


    private static final String TAG = "DBManger";

    public DBManager(){
    }

    //单例模式
    public static DBManager createInstance(){
        if(dbManger==null){
            dbManger=new DBManager();
        }
        return dbManger;
    }


    public void connectDB() {
        futureTask=new FutureTask<Object>(new Callable<Object>(){
            @Override
            public Object call() {
                Log.d(TAG, "connectDB: Connecting to database...");
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(URL, USER, PASS);
                    stmt = conn.createStatement();
                    Log.d(TAG, "connectDB: Connect to database successful.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    Log.d(TAG, "connectDB: Connect to database failure.");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return conn;
            }
        });
        executorService.submit(futureTask);
    }

    // 查询
    public ResultSet executeQuery(final String sql) {
        futureTask=new FutureTask<Object>(new Callable<Object>() {
            @Override
            public Object call() {
                Log.d(TAG, "executeQuery: querying...");
                try {
                    //conn = DriverManager.getConnection(URL, USER, PASS);
                    //stmt=conn.createStatement();
                    rs = stmt.executeQuery(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return rs;
            }
        });
        executorService.submit(futureTask);
        ResultSet rs=null;
        try {
            rs = (ResultSet) futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            return rs;
        }
    }

    // 关闭数据库 关闭对象，释放句柄
    public void closeDB() {
        Log.d(TAG, "closeDB: close connection to database..");
        try {
            stmt.close();
            conn.close();
            Log.d(TAG, "closeDB: close connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "closeDB: close connection failure");
        }
    }

    // 增添/删除/修改
    public int executeUpdate(final String sql) {
        int ret = 0;
        futureTask=new FutureTask<Object>(new Callable<Object>() {
            @Override
            public Object call() {
                int ret = 0;
                try {
                    ret = stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return ret;
            }
        });
        executorService.submit(futureTask);
        try {
            ret=(int)futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(ret==1)
            Log.d(TAG, "executeUpdate: update successfully");
        return ret;
    }
}

