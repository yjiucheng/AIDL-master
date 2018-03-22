package com.gupo.jiucheng.aidl_master.auto.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jiucheng on 2018/3/22.
 */

public class Student implements Parcelable {
    public String name;
    public String id;
    public String age;

    public Student() {
    }

    protected Student(Parcel in) {
        name = in.readString();
        id = in.readString();
        age = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
