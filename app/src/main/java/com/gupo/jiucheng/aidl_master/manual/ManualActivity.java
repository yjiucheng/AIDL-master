package com.gupo.jiucheng.aidl_master.manual;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gupo.jiucheng.aidl_master.R;
import com.gupo.jiucheng.aidl_master.auto.AutoActivity;
import com.gupo.jiucheng.aidl_master.manual.bean.Person;
import com.gupo.jiucheng.aidl_master.manual.mybinder.IManualRemote;
import com.gupo.jiucheng.aidl_master.manual.mybinder.ManualStub;

/**
 * 不依赖AIDL工具，手写远程Service完成跨进程通信
 */
public class ManualActivity extends AppCompatActivity {
    private TextView mStatusTv, mIdTv, mNameTv, mAgeTv;
    private IManualRemote remote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        initView();
    }

    private void initView() {
        mStatusTv = findViewById(R.id.tv_status);
        mIdTv = findViewById(R.id.tv_id);
        mNameTv = findViewById(R.id.tv_name);
        mAgeTv = findViewById(R.id.tv_age);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始链接
                connect();
            }
        });
        findViewById(R.id.id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remote == null) {
                    Toast.makeText(ManualActivity.this, "请先建立连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    mIdTv.setText("id:" + remote.getId());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remote == null) {
                    Toast.makeText(ManualActivity.this, "请先建立连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    mNameTv.setText("name:" + remote.getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.age).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remote == null) {
                    Toast.makeText(ManualActivity.this, "请先建立连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    mAgeTv.setText("age:" + remote.getAge());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.set_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remote == null) {
                    Toast.makeText(ManualActivity.this, "请先建立连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Person person = new Person();
                    person.name = "manual_周星星";
                    person.id = "manual_9527";
                    person.age = "manual_18";
                    remote.setData(person);
                    Toast.makeText(ManualActivity.this, "已更新", Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void connect() {
        Intent intent = new Intent(this, ManualService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remote = ManualStub.asInterface(service);
            mStatusTv.setText("手动创建服务端已连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connect();
        }
    };
}
