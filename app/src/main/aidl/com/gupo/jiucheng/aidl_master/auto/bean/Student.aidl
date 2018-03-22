package com.gupo.jiucheng.aidl_master.auto.bean;

import com.gupo.jiucheng.aidl_master.auto.bean.Student;

//我们在aidl文件需要引用自定义Student类，因此需要先声明
//注意：在这里声明的Student，路径必须与原有的Student类路径完全一致，不然编译后会报找不到类的错误
parcelable Student;