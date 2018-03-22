package com.gupo.jiucheng.aidl_master.manual.mybinder;

import android.os.Binder;
import android.os.IInterface;
import android.os.RemoteException;

import com.gupo.jiucheng.aidl_master.manual.bean.Person;

/**
 * Created by jiucheng on 2018/3/22.
 * 类似于aidl文件
 * 主要定义相关的抽象方法
 */

public interface IManualRemote extends IInterface {
    String DESCRIPTOR = "com.gupo.jiucheng.aidl_master.manual.mybinder.IManualRemote";
    int TRANSACTION_getName = (Binder.FIRST_CALL_TRANSACTION + 0);
    int TRANSACTION_getId = (Binder.FIRST_CALL_TRANSACTION + 1);
    int TRANSACTION_getAge = (Binder.FIRST_CALL_TRANSACTION + 2);
    int TRANSACTION_setData = (Binder.FIRST_CALL_TRANSACTION + 3);

    String getName() throws RemoteException;

    String getId() throws RemoteException;

    String getAge() throws RemoteException;

    void setData(Person person) throws RemoteException;
}
