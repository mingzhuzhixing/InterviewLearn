package com.v.module_recyclerview.swipe_refresh_layout;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.v.module_base.BaseTitleBarActivity;
import com.v.module_recyclerview.R;
import com.v.module_recyclerview.decoration.HorizontalDividerItemDecoration;
import com.v.module_recyclerview.listview.RecyclerViewAdapter;
import com.v.module_recyclerview.swipe_refresh_layout.refreshview.OnRefreshTextHelper;
import com.v.module_recyclerview.swipe_refresh_layout.refreshview.VerticalMouldingHeaderJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Recyclerview + SwipeRefreshLayout, 打造自定义下拉刷新,上拉加载
 */
public class SwipeRefreshLayoutActivity extends BaseTitleBarActivity {
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSwipeRefreshLayout;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_swipe_refresh_layout;
    }

    @Override
    protected String setTitle() {
        return "自定义下拉刷新,上拉加载";
    }

    @Override
    public void initData() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        VerticalMouldingHeaderJson refreshHeader = new VerticalMouldingHeaderJson(this);
        refreshHeader.setRefreshTextHelper(new OnRefreshTextHelper());
        mSwipeRefreshLayout.setRefreshHeader(refreshHeader);
    }

    @Override
    public void processLogical() {
        initRecyclerView();

        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("item" + i);
        }
        mAdapter.setList(mList);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        //设置分割线
        RecyclerView.ItemDecoration itemDecoration = new HorizontalDividerItemDecoration.Builder(this)
                .colorResId(R.color.color_4bba95)
                .sizeResId(R.dimen.y2)
                .build();
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new RecyclerViewAdapter(R.layout.item_listview_ver);
        mRecyclerView.setAdapter(mAdapter);
    }
}