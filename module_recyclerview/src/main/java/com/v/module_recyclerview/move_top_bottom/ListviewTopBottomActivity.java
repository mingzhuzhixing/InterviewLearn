package com.v.module_recyclerview.move_top_bottom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_recyclerview.R;
import com.v.module_recyclerview.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("NonConstantResourceId")
public class ListviewTopBottomActivity extends AppCompatActivity {

    @BindView(R2.id.listview)
    public ListView listview;

    private ListviewAdapter mAdapter;
    private Unbinder unbinder;

    private static final String KEY_ORIENTATION = "key_orientation";

    public static Intent newInstance(Context context, int orientation) {
        Intent intent = new Intent(context, ListviewTopBottomActivity.class);
        intent.putExtra(KEY_ORIENTATION, orientation);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_top_bottom);
        unbinder = ButterKnife.bind(this);
        initRecyclerView();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add("item" + i);
        }
        mAdapter = new ListviewAdapter(this,mList);
        listview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 去底部
     */
    public void onMoveBottom(View view) {
        //listview.smoothScrollToPosition(mAdapter.getCount()-1);
        //listview.scrollTo(0,1000);

        // 向下滚动 200px
//        listview.smoothScrollByOffset(200);
        listview.setSelection(mAdapter.getCount()-1);
    }

    /**
     * 去顶部
     */
    public void onMoveTop(View view) {
        //listview.smoothScrollToPosition(0);
        //listview.scrollTo(0,10);

        // 向上滚动 200px
//        listview.smoothScrollByOffset(-200);
        listview.setSelection(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}