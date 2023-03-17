package com.v.module_recyclerview.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.animation.BaseAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseRvAdapter
 * Description:
 */
public abstract  class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseRvHolder> {

    protected Activity supportFragment;
    public List<T> mList=new ArrayList<>();
    public String limitTime="";
    protected boolean isOpenAnimation= false;
    protected boolean isFirstOnly= false;
    protected BaseAnimation animation;
    protected int mLastPosition = 0;
    protected int mLastLoadPosition = 0;//分页加载时记录

    public BaseRvAdapter<T> setOpenAnimation(boolean openAnimation) {
        isOpenAnimation = openAnimation;
        return this;
    }

    public boolean isOpenAnimation() {
        return isOpenAnimation;
    }

    public BaseRvAdapter<T> setFirstOnly(boolean firstOnly) {
        isFirstOnly = firstOnly;
        return this;
    }

    public void setmLastLoadPosition() {
        this.mLastLoadPosition = mLastPosition;
    }

    public void openAnimation(BaseAnimation animation){
        isOpenAnimation = true;
        this.animation = animation;
    }

    public BaseRvAdapter(Activity mCtx) {
        this.supportFragment = mCtx;
    }

    public void clear(){
        if(mList!=null){
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void setData(List<T> list){
        if(list!=null){
            mList.addAll(list);
        }
        notifyDataSetChanged();

    }

    public void setData(T data) {
        if (data != null) {
            mList.add(data);
        }
        notifyDataSetChanged();

    }

    public void setData(T data,int position) {
        if (data != null) {
            mList.add(position,data);
        }
        notifyDataSetChanged();

    }

    public void setData(List<T> data, int position) {
        if (data != null) {
            mList.addAll(position, data);
        }
        notifyDataSetChanged();
    }

    public void setDataInsert(List<T> data, int position) {
        if (data != null) {
            mList.addAll(position, data);
        }
        notifyItemRangeInserted(position,data.size());
    }

    public void clearAndSetData(List<T> list){
        if(list!=null){
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void removeData(int position) {
        if (mList != null) {
            mList.remove(position);
        }
        notifyDataSetChanged();

    }

    public void removeData(T t) {
        if (mList != null) {
            mList.remove(t);
        }
        notifyDataSetChanged();

    }
    public void setLimitTime(String limitTime){
        this.limitTime =limitTime;
    }

    @Override
    public BaseRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = supportFragment;
        if (context == null) {
            context = parent.getContext();
        }
        View itemView = LayoutInflater.from(context).inflate(viewType, parent, false);
        return BaseRvHolder.createViewHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(BaseRvHolder holder, int position) {
        convert(holder, getValue(position), getItemViewType(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutAsViewType(getValue(position), position);
    }

    public List<T> getValueList() {
        return mList;
    }

    public T getValue(int position) {
        return getValueList().get(position);
    }

    public abstract void convert(BaseRvHolder holder, T t, int layoutType, int position);

    @LayoutRes
    public abstract int getLayoutAsViewType(T t, int position);
}
