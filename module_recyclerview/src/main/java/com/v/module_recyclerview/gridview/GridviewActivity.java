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
import com.v.module_utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("NonConstantResourceId")
public class GridviewActivity extends AppCompatActivity {

    @BindView(R2.id.recyclerview)
    public RecyclerView recyclerView;

    private ListviewAdapter mAdapter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        unbinder = ButterKnife.bind(this);
        initRecyclerView();
        initData();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        int spanCount = 4;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        //设置分割线 item居中显示
        float space = ScreenUtils.getScreenWidthPixels(this) - ScreenUtils.dip2px(this, 80) * spanCount;
        recyclerView.addItemDecoration(new GridItemCenterDecoration(spanCount, (int) (space / (spanCount - 1)), false));
        mAdapter = new ListviewAdapter(R.layout.item_gridview);
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add("item" + (i < 10 ? "0" + i : i));
        }

        mAdapter.setList(mList);
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