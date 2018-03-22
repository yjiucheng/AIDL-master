package com.gupo.jiucheng.aidl_master.manual.mybinder;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.gupo.jiucheng.aidl_master.manual.bean.Person;

/**
 * Created by jiucheng on 2018/3/22.
 */

public class ManualProxy implements IManualRemote {
    private IBinder iBinder;

    public ManualProxy(IBinder iBinder) {
        this.iBinder = iBinder;
    }

    @Override
    public IBinder asBinder() {
        return iBinder;
    }

    public String getInterfaceDescriptor() {
        return DESCRIPTOR;
    }

    @Override
    public String getName() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        String name;
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            iBinder.transact(TRANSACTION_getName, data, reply, 0);
            reply.readException();
            name = reply.readString();
        } finally {
            data.recycle();
            reply.recycle();
        }
        return name;
    }

    @Override
    public String getId() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        String id;
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            iBinder.transact(TRANSACTION_getId, data, reply, 0);
            reply.readException();
            id = reply.readString();
        } finally {
            data.recycle();
            reply.recycle();
        }
        return id;
    }

    @Override
    public String getAge() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        String age;
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            iBinder.transact(TRANSACTION_getName, data, reply, 0);
            reply.readException();
            age = reply.readString();
        } finally {
            data.recycle();
            reply.recycle();
        }
        return age;
    }

    @Override
    public void setData(Person person) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            if (person != null) {
                data.writeInt(1);
                person.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            iBinder.transact(TRANSACTION_setData, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }
}
