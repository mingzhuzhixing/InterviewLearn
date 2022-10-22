package com.shuyu.textutillib.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 话题model
 * Created by guoshuyu on 2017/8/16.
 */

public class TopicModel implements Parcelable {
    /**
     * 话题名字内部不能有#和空格
     */
    private String topicName;
    private String topicId;

    public TopicModel() {

    }

    public TopicModel(String topicName, String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
    }

    protected TopicModel(Parcel in) {
        topicName = in.readString();
        topicId = in.readString();
    }

    public static final Creator<TopicModel> CREATOR = new Creator<TopicModel>() {
        @Override
        public TopicModel createFromParcel(Parcel in) {
            return new TopicModel(in);
        }

        @Override
        public TopicModel[] newArray(int size) {
            return new TopicModel[size];
        }
    };

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return this.topicName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(topicName);
        dest.writeString(topicId);
    }
}
