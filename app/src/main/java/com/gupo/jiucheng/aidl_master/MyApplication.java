package com.gupo.jiucheng.aidl_master;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * Created by jiucheng on 2018/4/11.
 * 功能：
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        int pid = android.os.Process.myPid();
        Log.e("当前进程pid=", pid + "");
        long time = System.currentTimeMillis();
        String pName = getProcessName(this, pid);
        Log.e("方法一获取进程名称耗时：", (System.currentTimeMillis() - time) + "");
        long times = System.currentTimeMillis();
        String aName = getProcessName();
        Log.e("方法二获取进程名称耗时：", (System.currentTimeMillis() - times) + "");

        Log.e("当前进程pName=", pName + "");
        Log.e("当前进程aName=", aName + "");
        Log.e("当前进程Application=", this.toString());
    }

    public String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager)
                cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    public static String getProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
