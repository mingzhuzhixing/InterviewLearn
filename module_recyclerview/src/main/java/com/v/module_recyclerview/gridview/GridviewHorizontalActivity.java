package com.v.module_recyclerview.gridview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.v.module_recyclerview.R;
import com.v.module_recyclerview.R2;
import com.v.module_recyclerview.decoration.GridItemCenterDecoration;
import com.v.module_recyclerview.listview.ListviewAdapter;
import com.v.module_utils.DensityUtils;
import com.v.module_utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("NonConstantResourceId")
public class GridviewHorizontalActivity extends AppCompatActivity {

    @BindView(R2.id.recyclerview)
    public RecyclerView recyclerView;

    private Unbinder unbinder;

    private final List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        unbinder = ButterKnife.bind(this);
        initData();
        initRecyclerView();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        int spanCount = mList.size();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        ListviewAdapter mAdapter = new ListviewAdapter(R.layout.item_gridview_2);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setList(mList);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mList.clear();
        for (int i = 0; i < 5; i++) {
            mList.add("item" + (i < 10 ? "0" + i : i));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * Android中dp和pix互相转化
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}