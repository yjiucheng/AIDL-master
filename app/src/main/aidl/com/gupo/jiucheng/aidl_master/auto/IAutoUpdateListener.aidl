// IAutoUpdateListener.aidl
package com.gupo.jiucheng.aidl_master.auto;

// Declare any non-default types here with import statements
import com.gupo.jiucheng.aidl_master.auto.bean.Student;

interface IAutoUpdateListener {
    void update(in Student data);
}
