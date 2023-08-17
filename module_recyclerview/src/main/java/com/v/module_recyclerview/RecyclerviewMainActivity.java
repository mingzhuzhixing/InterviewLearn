package com.v.module_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_recyclerview.ceiling.CeilingActivity;
import com.v.module_recyclerview.drag_recyclerview.DragListviewActivity;
import com.v.module_recyclerview.expand_recyclerview.ExpandRecyclerViewActivity;
import com.v.module_recyclerview.gridview.GridviewActivity;
import com.v.module_recyclerview.gridview.GridviewFullActivity;
import com.v.module_recyclerview.gridview.GridviewHorizontalActivity;
import com.v.module_recyclerview.listview.ListviewActivity;
import com.v.module_recyclerview.overscroll.OverscrollRecyclerViewActivity;
import com.v.module_recyclerview.snap_helper.SnapHelperActivity;
import com.v.module_recyclerview.sticky_decoration.PowerfulStickyActivity;
import com.v.module_recyclerview.sticky_decoration.PowerfulStickyGridActivity;
import com.v.module_recyclerview.sticky_decoration.StickyActivity;
import com.v.module_recyclerview.sticky_decoration.StickyGridActivity;
import com.v.module_recyclerview.swipe_refresh_layout.SwipeNoRefreshLayoutActivity;
import com.v.module_recyclerview.swipe_refresh_layout.SwipeRefreshLayoutActivity;
import com.v.module_recyclerview.waterfall_flow.WaterfallFlowActivity;

public class RecyclerviewMainActivity extends BaseTitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview_main;
    }

    @Override
    protected String setTitle() {
        return "Recyclerview";
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
     * gridview 横向摆列
     */
    public void gridviewHorClick(View view) {
        startActivity(new Intent(this, GridviewHorizontalActivity.class));
    }

    /**
     * gridview
     */
    public void gridviewFullClick(View view) {
        startActivity(new Intent(this, GridviewFullActivity.class));
    }

    /**
     * 瀑布流
     */
    public void waterfallFlowClick(View view) {
        startActivity(new Intent(this, WaterfallFlowActivity.class));
    }

    /**
     * 自定义下拉刷新,上拉加载
     */
    public void swipeRefreshLayoutClick(View view) {
        startActivity(new Intent(this, SwipeRefreshLayoutActivity.class));
    }

    /**
     * 自定义下拉刷新,上拉加载
     */
    public void swipeNoRefreshLayoutClick(View view) {
        startActivity(new Intent(this, SwipeNoRefreshLayoutActivity.class));
    }

    /**
     * 列表拖拽
     */
    public void dragClick(View view) {
        startActivity(new Intent(this, DragListviewActivity.class));
    }

    /**
     * 阻尼效果
     */
    public void overScrollClick(View view) {
        startActivity(new Intent(this, OverscrollRecyclerViewActivity.class));
    }


    /**
     * 可展开二级列表expand效果
     */
    public void expandRecyclerViewClick(View view) {
        startActivity(new Intent(this, ExpandRecyclerViewActivity.class));
    }


    /**
     * 吸顶效果
     */
    public void ceilingRecyclerViewClick(View view) {
        startActivity(new Intent(this, CeilingActivity.class));
    }

    /**
     *
     */
    public void stickViewClick(View view) {
        startActivity(new Intent(this, StickyActivity.class));
    }

    /**
     *
     */
    public void stickGridViewClick(View view) {
        startActivity(new Intent(this, StickyGridActivity.class));
    }

    /**
     *
     */
    public void powerfulStickViewClick(View view) {
        startActivity(new Intent(this, PowerfulStickyActivity.class));
    }

    /**
     *
     */
    public void powerfulStickGridViewClick(View view) {
        startActivity(new Intent(this, PowerfulStickyGridActivity.class));
    }

    /**
     *
     */
    public void expandableStickViewClick(View view) {
        startActivity(new Intent(this, ExpandRecyclerViewActivity.class));
    }

}