package com.gupo.jiucheng.aidl_master.manual;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.gupo.jiucheng.aidl_master.manual.bean.Person;
import com.gupo.jiucheng.aidl_master.manual.mybinder.ManualStub;

public class ManualService extends Service {
    private IBinder iBinder;
    private Person person;

    public ManualService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (iBinder == null) {
            iBinder = new ManualStub() {
                @Override
                public String getName() throws RemoteException {
                    if (person == null) {
                        return "未设置数据：name=null";
                    }
                    return person.name;
                }

                @Override
                public String getId() throws RemoteException {
                    if (person == null) {
                        return "未设置数据：id=null";
                    }
                    return person.id;
                }

                @Override
                public String getAge() throws RemoteException {
                    if (person == null) {
                        return "未设置数据：age=null";
                    }
                    return person.age;
                }

                @Override
                public void setData(Person person) throws RemoteException {
                    ManualService.this.person = person;
                }
            };
        }
        return iBinder;
    }
}
