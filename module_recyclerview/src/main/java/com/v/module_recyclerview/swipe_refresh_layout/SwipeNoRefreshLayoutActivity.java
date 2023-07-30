package com.v.module_recyclerview.swipe_refresh_layout;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.v.module_recyclerview.R;
import com.v.module_recyclerview.decoration.HorizontalDividerItemDecoration;
import com.v.module_recyclerview.listview.ListviewAdapter;
import com.v.module_recyclerview.swipe_refresh_layout.refreshview.OnRefreshTextHelper;
import com.v.module_recyclerview.swipe_refresh_layout.refreshview.VerticalMouldingHeaderJson;
import com.v.module_recyclerview.widget.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Recyclerview + SwipeRefreshLayout, 打造上拉加载
 */
public class SwipeNoRefreshLayoutActivity extends AppCompatActivity implements OnLoadMoreListener {
    private MyRecyclerView mRecyclerView;
    private MyRecyclerView mRvTodayPlan;
    private SmartRefreshLayout mSwipeRefreshLayout;
    private ListviewAdapter mAdapter;
    private ListviewAdapter mTodayAdapter;
    private final List<String> allList = new ArrayList<>();
    private final List<String> mTodayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_no_refresh_layout);
        initData();
        processLogical();
    }

    /**
     * 初始化数据
     */
    public void initData() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRvTodayPlan = findViewById(R.id.rv_today_plan);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        VerticalMouldingHeaderJson refreshHeader = new VerticalMouldingHeaderJson(this);
        refreshHeader.setRefreshTextHelper(new OnRefreshTextHelper());
        mSwipeRefreshLayout.setRefreshHeader(refreshHeader);
        mSwipeRefreshLayout.autoLoadMore();
        mSwipeRefreshLayout.setEnableRefresh(false);
        mSwipeRefreshLayout.setOnLoadMoreListener(this);
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
        mAdapter = new ListviewAdapter(R.layout.item_listview_ver_2);
        mRecyclerView.setAdapter(mAdapter);

        mRvTodayPlan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //设置分割线
        mRvTodayPlan.addItemDecoration(itemDecoration);
        mTodayAdapter = new ListviewAdapter(R.layout.item_listview_ver_3);
        mRvTodayPlan.setAdapter(mTodayAdapter);
    }

    /**
     * 逻辑处理
     */
    public void processLogical() {
        initRecyclerView();
        for (int i = 0; i < 10; i++) {
            allList.add("item" + i);
        }
        mAdapter.setList(allList);

        for (int i = 0; i < 2; i++) {
            mTodayList.add("item" + i);
        }
        mTodayAdapter.setList(mTodayList);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        int size = allList.size();
        for (int i = size; i < size + 10; i++) {
            allList.add("item" + i);
        }
        mAdapter.setList(allList);
        refreshLayout.finishLoadMore();
    }
}