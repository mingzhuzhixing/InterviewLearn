package com.v.module_recyclerview.drag_recyclerview;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.v.module_recyclerview.R;

/**
 * 拖拽 listview 适配器
 */
public class DragListviewAdapter extends BaseQuickAdapter<String, BaseViewHolder> implements DraggableModule {

    public DragListviewAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, String s) {
        holder.setText(R.id.tv_title, s);
    }
}
