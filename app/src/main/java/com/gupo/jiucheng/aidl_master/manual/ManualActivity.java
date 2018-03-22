package com.gupo.jiucheng.aidl_master.manual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gupo.jiucheng.aidl_master.R;

/**
 * 不依赖AIDL工具，手写远程Service完成跨进程通信
 */
public class ManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
    }
}
