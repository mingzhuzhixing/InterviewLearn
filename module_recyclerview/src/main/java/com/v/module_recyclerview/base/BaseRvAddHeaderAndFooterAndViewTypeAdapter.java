package com.v.module_recyclerview.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by songzhf on 2018/10/9.
 */

public abstract class BaseRvAddHeaderAndFooterAndViewTypeAdapter<T extends ViewTypeData> extends BaseRvAdapter<T> {
    private RecyclerView mRecyclerView;
    private View VIEW_FOOTER;
    private View VIEW_HEADER;

    private final static int TYPE_NORMAL = 1000;
    private final static int TYPE_HEADER = 1001;
    private final static int TYPE_FOOTER = 1002;

    private Map<Integer, Integer> viewTypeAndLayoutIdMap = new HashMap<>();

    public BaseRvAddHeaderAndFooterAndViewTypeAdapter(Activity mCtx, Map<Integer, Integer> viewTypeAndLayoutIdMap) {
        super(mCtx);
        this.viewTypeAndLayoutIdMap = viewTypeAndLayoutIdMap;
    }

    @Override
    public BaseRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return BaseRvHolder.createViewHolder(supportFragment, VIEW_HEADER);
        } else if (viewType == TYPE_FOOTER) {
            return BaseRvHolder.createViewHolder(supportFragment, VIEW_FOOTER);
        } else {
//            if (viewType == viewTypeAndLayoutIdMap.get())
//            Log.e("dada_debug",supportFragment+">>>>"+viewTypeAndLayoutIdMap+" "+viewType);
            int layout_id = 0;
            try {
                layout_id = viewTypeAndLayoutIdMap.get(new Integer(viewType));
//                layout_id = viewTypeAndLayoutIdMap.get(viewType);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return BaseRvHolder.createViewHolder(supportFragment, getLayout(layout_id, parent));
        }
    }

    @Override
    public void onBindViewHolder(BaseRvHolder holder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        int count = mList != null ? mList.size() : 0;
        if (VIEW_FOOTER != null) count++;
        if (VIEW_HEADER != null) count++;
        return count;
    }


    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            int pos = position;
            if (haveHeaderView()) {
                pos = position - 1;
            }
            ViewTypeData value = getValue(pos);
            return value.viewType;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addHeaderView(int headerLayoutId) {
        addHeaderView(LayoutInflater.from(supportFragment).inflate(headerLayoutId, null));
    }

    public void addHeaderView(View header) {
        if (haveHeaderView()) {
//            throw new IllegalStateException("header view has already exist!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            header.setLayoutParams(params);
            VIEW_HEADER = header;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }
    }

    public void addFooterView(int footerLayoutId) {
        addFooterView(LayoutInflater.from(supportFragment).inflate(footerLayoutId, null));
    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
//            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }


    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
                }
            });
        }
    }

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    private boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    private View getLayout(int layoutId, ViewGroup parent) {
        if (layoutId <= 0) {
            return LayoutInflater.from(supportFragment).inflate(getLayoutId(), parent, false);
        }
        return LayoutInflater.from(supportFragment).inflate(layoutId, parent, false);
    }

    public abstract int getLayoutId();

    //废弃
    @Override
    public int getLayoutAsViewType(T t, int position) {
        return 0;
    }
}
