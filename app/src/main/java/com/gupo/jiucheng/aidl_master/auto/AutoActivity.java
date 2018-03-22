package com.gupo.jiucheng.aidl_master.auto;

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
import com.gupo.jiucheng.aidl_master.auto.bean.Student;

/**
 * 依赖AIDL工具，自动实现跨进程通信
 */
public class AutoActivity extends AppCompatActivity {
    private IAutoAidl binder;
    private TextView mStatusTv, mIdTv, mNameTv, mAgeTv, mNewDataTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        initView();
    }

    private void initView() {
        mStatusTv = findViewById(R.id.tv_status);
        mIdTv = findViewById(R.id.tv_id);
        mNameTv = findViewById(R.id.tv_name);
        mAgeTv = findViewById(R.id.tv_age);
        mNewDataTv = findViewById(R.id.tv_new_data);
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
                if (binder == null) {
                    Toast.makeText(AutoActivity.this, "请先建立连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    mIdTv.setText("id:" + binder.getId());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder == null) {
                    Toast.makeText(AutoActivity.this, "请先建立连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    mNameTv.setText("name:" + binder.getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.age).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder == null) {
                    Toast.makeText(AutoActivity.this, "请先建立连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    mAgeTv.setText("age:" + binder.getAge());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.set_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder == null) {
                    Toast.makeText(AutoActivity.this, "请先建立连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Student student = new Student();
                    student.id = "9527";
                    student.name = "周星星";
                    student.age = "18";
                    binder.setStudent(student);
                    Toast.makeText(AutoActivity.this, "已更新", Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.update_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder == null) {
                    Toast.makeText(AutoActivity.this, "请先建立连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    binder.updateByServer();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void connect() {
        Intent intent = new Intent(this, AutoService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    IAutoUpdateListener listener = new IAutoUpdateListener.Stub() {
        @Override
        public void update(Student data) throws RemoteException {
            mNewDataTv.setText("服务端更新数据：" + data.toString());
        }
    };

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = IAutoAidl.Stub.asInterface(service);
            try {
                binder.setListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mStatusTv.setText("连接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connect();
        }
    };

}
