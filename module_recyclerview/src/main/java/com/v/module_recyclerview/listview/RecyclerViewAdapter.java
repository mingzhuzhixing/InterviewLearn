package com.v.module_recyclerview.listview;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.v.module_recyclerview.R;

/**
 * RecyclerView 适配器
 */
public class RecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RecyclerViewAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, String s) {
        holder.setText(R.id.tv_title, s);
    }
}