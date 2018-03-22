package com.gupo.jiucheng.aidl_master.manual.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jiucheng on 2018/3/22.
 */

public class Person implements Parcelable {
    public String name;
    public String id;
    public String age;

    protected Person(Parcel in) {
        name = in.readString();
        id = in.readString();
        age = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
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
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
