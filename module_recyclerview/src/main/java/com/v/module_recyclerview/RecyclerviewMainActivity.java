package com.v.module_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.v.module_recyclerview.gridview.GridviewActivity;
import com.v.module_recyclerview.listview.ListviewActivity;
import com.v.module_recyclerview.snap_helper.SnapHelperActivity;
import com.v.module_recyclerview.waterfall_flow.WaterfallFlowActivity;

public class RecyclerviewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_main);
    }

    public void snapHelper(View view) {
        startActivity(new Intent(this, SnapHelperActivity.class));
    }

    /**
     * 竖向listview
     */
    public void listviewVerClick(View view) {
        startActivity(ListviewActivity.newInstance(this, LinearLayoutManager.VERTICAL));
    }

    /**
     * 横向listview
     */
    public void listviewHorClick(View view) {
        startActivity(ListviewActivity.newInstance(this, LinearLayoutManager.HORIZONTAL));
    }

    /**
     * gridview
     */
    public void gridviewClick(View view) {
        startActivity(new Intent(this, GridviewActivity.class));
    }

    /**
     * 瀑布流
     */
    public void waterfallFlowClick(View view) {
        startActivity(new Intent(this, WaterfallFlowActivity.class));
    }
}