package com.shuyu.textutillib.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 用户model
 * Created by shuyu on 2016/11/10.
 */

public class UserModel implements Parcelable {

    public UserModel() {

    }

    public UserModel(String user_name, String user_id) {
        this.user_name = user_name;
        this.user_id = user_id;
    }

    /**
     * 名字不能带@和空格
     */
    private String user_name;

    private String user_id;

    protected UserModel(Parcel in) {
        user_name = in.readString();
        user_id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_name);
        dest.writeString(user_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return this.user_name;
    }
}
