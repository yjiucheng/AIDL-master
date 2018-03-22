// IAutoAidl.aidl
package com.gupo.jiucheng.aidl_master.auto;

import com.gupo.jiucheng.aidl_master.auto.bean.Student;
import com.gupo.jiucheng.aidl_master.auto.IAutoUpdateListener;

interface IAutoAidl {
    String getName();
    String getId();
    String getAge();
    void setStudent(in Student data);
    void setListener(IAutoUpdateListener listener);
    void updateByServer();
}
