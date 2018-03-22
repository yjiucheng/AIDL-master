package com.gupo.jiucheng.aidl_master.auto;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.gupo.jiucheng.aidl_master.auto.bean.Student;

/**
 * 自动实现跨进程通信service
 * 注意：需要在AndroidManifest文件设置process属性
 */
public class AutoService extends Service {
    private Binder binder;
    private Student student;
    private IAutoUpdateListener listener;

    public AutoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (binder == null) {
            binder = new IAutoAidl.Stub() {
                @Override
                public String getName() throws RemoteException {
                    if (student == null) {
                        return "未设置数据，name==null";
                    }
                    return student.name;
                }

                @Override
                public String getId() throws RemoteException {
                    if (student == null) {
                        return "未设置数据，id==null";
                    }
                    return student.id;
                }

                @Override
                public String getAge() throws RemoteException {
                    if (student == null) {
                        return "未设置数据，age==null";
                    }
                    return student.age;
                }

                @Override
                public void setStudent(Student data) throws RemoteException {
                    student = data;
                }

                @Override
                public void setListener(IAutoUpdateListener listener) throws RemoteException {
                    AutoService.this.listener = listener;
                }

                @Override
                public void updateByServer() throws RemoteException {
                    Student st = new Student();
                    st.name = "server_周星星";
                    st.age = "server_18";
                    st.id = "server_9527";
                    listener.update(st);
                }
            };
        }
        return binder;
    }
}
