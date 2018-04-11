package com.gupo.jiucheng.aidl_master;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gupo.jiucheng.aidl_master.auto.AutoActivity;
import com.gupo.jiucheng.aidl_master.manual.ManualActivity;
import com.gupo.jiucheng.aidl_master.messenger.MessengerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.auto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自动
                startActivity(new Intent(MainActivity.this, AutoActivity.class));
            }
        });
        findViewById(R.id.manual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //手动
                startActivity(new Intent(MainActivity.this, ManualActivity.class));
            }
        });

        findViewById(R.id.messenger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //messenger
                startActivity(new Intent(MainActivity.this, MessengerActivity.class));
            }
        });
    }
}
