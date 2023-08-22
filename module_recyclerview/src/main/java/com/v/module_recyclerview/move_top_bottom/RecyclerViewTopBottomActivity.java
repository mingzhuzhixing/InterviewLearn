package com.v.module_recyclerview.move_top_bottom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.v.module_recyclerview.R;
import com.v.module_recyclerview.R2;
import com.v.module_recyclerview.decoration.HorizontalDividerItemDecoration;
import com.v.module_recyclerview.decoration.VerticalDividerItemDecoration;
import com.v.module_recyclerview.listview.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("NonConstantResourceId")
public class RecyclerViewTopBottomActivity extends AppCompatActivity {

    @BindView(R2.id.recyclerview)
    public RecyclerView recyclerView;

    private RecyclerViewAdapter mAdapter;
    private Unbinder unbinder;

    private static final String KEY_ORIENTATION = "key_orientation";

    /**
     * 方向
     */
    private int mOrientation = LinearLayoutManager.VERTICAL;

    public static Intent newInstance(Context context, int orientation) {
        Intent intent = new Intent(context, RecyclerViewTopBottomActivity.class);
        intent.putExtra(KEY_ORIENTATION, orientation);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_top_bottom);
        unbinder = ButterKnife.bind(this);
        mOrientation = getIntent().getIntExtra(KEY_ORIENTATION, LinearLayoutManager.VERTICAL);
        initRecyclerView();
        initData();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            //设置分割线
            RecyclerView.ItemDecoration itemDecoration = new VerticalDividerItemDecoration.Builder(this)
                    .colorResId(R.color.color_4bba95)
                    .sizeResId(R.dimen.y2)
                    .build();
            recyclerView.addItemDecoration(itemDecoration);
            mAdapter = new RecyclerViewAdapter(R.layout.item_listview_hor);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            //设置分割线
            RecyclerView.ItemDecoration itemDecoration = new HorizontalDividerItemDecoration.Builder(this)
                    .colorResId(R.color.color_4bba95)
                    .sizeResId(R.dimen.y2)
                    .build();
            recyclerView.addItemDecoration(itemDecoration);
            mAdapter = new RecyclerViewAdapter(R.layout.item_listview_ver);
        }

        recyclerView.setAdapter(mAdapter);
    }


    public void onMoveTop(View view) {
        // 滚动到顶部（无动画）
        recyclerView.scrollToPosition(0);
    }

    public void onMoveBottom(View view) {
        // 滚动到底部（无动画）
        recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add("item" + i);
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
}