package com.gupo.jiucheng.aidl_master.manual.mybinder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.gupo.jiucheng.aidl_master.manual.bean.Person;

/**
 * Created by jiucheng on 2018/3/22.
 */

public abstract class ManualStub extends Binder implements IManualRemote {

    public ManualStub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IManualRemote asInterface(IBinder obj) {
        if (obj == null) {
            return null;
        }
        IInterface iInterface = obj.queryLocalInterface(DESCRIPTOR);
        if ((iInterface != null) && (iInterface instanceof IManualRemote)) {
            return (IManualRemote) iInterface;
        }
        return new ManualProxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
            case TRANSACTION_getName:
                data.enforceInterface(DESCRIPTOR);
                String name = this.getName();
                reply.writeNoException();
                reply.writeString(name);
                return true;
            case TRANSACTION_getId:
                data.enforceInterface(DESCRIPTOR);
                String id = this.getId();
                reply.writeNoException();
                reply.writeString(id);
                return true;
            case TRANSACTION_getAge:
                data.enforceInterface(DESCRIPTOR);
                String age = this.getAge();
                reply.writeNoException();
                reply.writeString(age);
                return true;
            case TRANSACTION_setData:
                data.enforceInterface(DESCRIPTOR);
                Person person;
                if (0 != data.readInt()) {
                    person = Person.CREATOR.createFromParcel(data);
                } else {
                    person = null;
                }
                this.setData(person);
                reply.writeNoException();
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }
}
