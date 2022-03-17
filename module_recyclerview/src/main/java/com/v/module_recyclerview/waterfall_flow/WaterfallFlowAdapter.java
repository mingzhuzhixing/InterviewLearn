package com.v.module_recyclerview.waterfall_flow;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.v.module_recyclerview.R;

import java.util.List;

/**
 * 瀑布流适配器
 */
public class WaterfallFlowAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public WaterfallFlowAdapter(int layoutResId) {
        super(layoutResId);
    }

    public WaterfallFlowAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, Integer integer) {
        ImageView ivIcon = holder.findView(R.id.iv_item_icon);
        if (ivIcon != null) {
            ivIcon.setImageResource(integer);
        }
    }
}
