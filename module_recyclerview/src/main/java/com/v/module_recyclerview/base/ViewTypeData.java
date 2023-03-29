package com.v.module_recyclerview.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by songzhf on 2019/3/15.
 */

public class ViewTypeData implements Parcelable {
    public int viewType;

    public static final Creator<ViewTypeData> CREATOR = new Creator<ViewTypeData>() {
        @Override
        public ViewTypeData createFromParcel(Parcel in) {
            return new ViewTypeData(in);
        }

        @Override
        public ViewTypeData[] newArray(int size) {
            return new ViewTypeData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.viewType);
    }

    public ViewTypeData() {
    }

    protected ViewTypeData(Parcel in) {
        this.viewType = in.readInt();
    }

}
