package com.v.module_recyclerview.gridview;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.v.module_recyclerview.R;
import com.v.module_recyclerview.R2;
import com.v.module_recyclerview.decoration.GridItemDecoration;
import com.v.module_utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 填充左右
 */
@SuppressLint("NonConstantResourceId")
public class GridviewFullActivity extends AppCompatActivity {

    @BindView(R2.id.recyclerview_1)
    public RecyclerView recyclerview_1;

    @BindView(R2.id.recyclerview_2)
    public RecyclerView recyclerview_2;

    @BindView(R2.id.recyclerview_3)
    public RecyclerView recyclerview_3;

    @BindView(R2.id.recyclerview_4)
    public RecyclerView recyclerview_4;

    @BindView(R2.id.recyclerview_5)
    public RecyclerView recyclerview_5;

    @BindView(R2.id.recyclerview_6)
    public RecyclerView recyclerview_6;

    @BindView(R2.id.recyclerview_7)
    public RecyclerView recyclerview_7;

    @BindView(R2.id.recyclerview_8)
    public RecyclerView recyclerview_8;

    @BindView(R2.id.recyclerview_9)
    public RecyclerView recyclerview_9;

    private Unbinder unbinder;
    private int mSpace = 0;
    private float mImageWidth = 0;

    private String[] images = {
            "https://img.youshu.cc/readwith/testNote/0b62cdbcb397b3df69a17db67f3c5e17.jpg",
            "https://img.youshu.cc/readwith/testNote/1483523bb2431b99ca9d92ba78ab1e47.jpg",
            "https://img.youshu.cc/readwith/testNote/63511f7b9db1c9474373b90d8ca2fed8.jpg",
            "https://img.youshu.cc/readwith/testNote/d003f19a25dc3d2722344a856c9ea24a.jpg",
            "https://img.youshu.cc/readwith/testNote/3928419c5157d3ea6b0758e67ad58010.jpg",
            "https://img.youshu.cc/readwith/testNote/d2a0e4991d7ac71565fc75f55c2a9cca.jpg",
            "https://img.youshu.cc/readwith/testNote/8ff7847659518ba8e637676ffcb0074d.jpg",
            "https://img.youshu.cc/readwith/testNote/0b024f239294abdb712eb0dcf8611f01.jpg",
            "https://img.youshu.cc/readwith/testNote/9b97296271f19fe2d93d2542d9ab2464.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_full);
        unbinder = ButterKnife.bind(this);
        initData();
        initRecyclerView1();
        initRecyclerView2();
        initRecyclerView3();
        initRecyclerView4();
        initRecyclerView5();
        initRecyclerView6();
        initRecyclerView7();
        initRecyclerView8();
        initRecyclerView9();
    }

    private void initData() {
        mSpace = (int) getResources().getDimension(R.dimen.x10);
        int widthPixels = ScreenUtils.getScreenWidthPixels(this);
        float marginLeft = getResources().getDimension(R.dimen.x30);
        mImageWidth = (widthPixels - mSpace * 2 - marginLeft * 2) / 3;
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView1() {
        int spanCount = 1;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerview_1.setLayoutManager(gridLayoutManager);
        //设置分割线 item居中显示
        Card_129_item_adapter mAdapter = new Card_129_item_adapter(this, R.layout.card_129_item);
        mAdapter.setType(1);
        recyclerview_1.setAdapter(mAdapter);
        List<String> list = new ArrayList<>();
        list.add(images[0]);
        mAdapter.setList(list);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView2() {
        int spanCount = 2;
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
//        builder.horColor(R.color.red);
//        builder.verColor(R.color.blue_00);
        builder.horSize(mSpace);
        builder.verSize(mSpace);
        builder.showLastDivider(false);
        recyclerview_2.addItemDecoration(builder.build());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerview_2.setLayoutManager(gridLayoutManager);
        //设置分割线 item居中显示
        Card_129_item_adapter mAdapter = new Card_129_item_adapter(this, R.layout.card_129_item, mImageWidth);
        recyclerview_2.setAdapter(mAdapter);
        List<String> list = new ArrayList<>();
        list.add(images[0]);
        list.add(images[1]);
        mAdapter.setList(list);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView3() {
        int spanCount = 3;
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
//        builder.horColor(R.color.red);
//        builder.verColor(R.color.blue_00);
        builder.horSize(mSpace);
        builder.verSize(mSpace);
        builder.showLastDivider(false);
        recyclerview_3.addItemDecoration(builder.build());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerview_3.setLayoutManager(gridLayoutManager);
        //设置分割线 item居中显示
        Card_129_item_adapter mAdapter = new Card_129_item_adapter(this, R.layout.card_129_item, mImageWidth);
        recyclerview_3.setAdapter(mAdapter);
        List<String> list = new ArrayList<>();
        list.add(images[0]);
        list.add(images[1]);
        list.add(images[2]);
        mAdapter.setList(list);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView4() {
        int spanCount = 2;
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
//        builder.horColor(R.color.red);
//        builder.verColor(R.color.blue_00);
        builder.horSize(mSpace);
        builder.verSize(mSpace);
        builder.showLastDivider(false);
        recyclerview_4.addItemDecoration(builder.build());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerview_4.setLayoutManager(gridLayoutManager);
        //设置分割线 item居中显示
        Card_129_item_adapter mAdapter = new Card_129_item_adapter(this, R.layout.card_129_item, mImageWidth);
        recyclerview_4.setAdapter(mAdapter);
        List<String> list = new ArrayList<>();
        list.add(images[0]);
        list.add(images[1]);
        list.add(images[2]);
        list.add(images[3]);
        mAdapter.setList(list);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView5() {
        int spanCount = 3;
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
//        builder.horColor(R.color.red);
//        builder.verColor(R.color.blue_00);
        builder.horSize(mSpace);
        builder.verSize(mSpace);
        builder.showLastDivider(false);
        recyclerview_5.addItemDecoration(builder.build());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerview_5.setLayoutManager(gridLayoutManager);
        //设置分割线 item居中显示
        Card_129_item_adapter mAdapter = new Card_129_item_adapter(this, R.layout.card_129_item, mImageWidth);
        recyclerview_5.setAdapter(mAdapter);
        List<String> list = new ArrayList<>();
        list.add(images[0]);
        list.add(images[1]);
        list.add(images[2]);
        list.add(images[3]);
        list.add(images[4]);
        mAdapter.setList(list);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView6() {
        int spanCount = 3;
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
//        builder.horColor(R.color.red);
//        builder.verColor(R.color.blue_00);
        builder.horSize(mSpace);
        builder.verSize(mSpace);
        builder.showLastDivider(false);
        recyclerview_6.addItemDecoration(builder.build());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerview_6.setLayoutManager(gridLayoutManager);
        //设置分割线 item居中显示
        Card_129_item_adapter mAdapter = new Card_129_item_adapter(this, R.layout.card_129_item, mImageWidth);
        recyclerview_6.setAdapter(mAdapter);
        List<String> list = new ArrayList<>();
        list.add(images[0]);
        list.add(images[1]);
        list.add(images[2]);
        list.add(images[3]);
        list.add(images[4]);
        list.add(images[5]);
        mAdapter.setList(list);
    }
    /**
     * 初始化recyclerview
     */
    private void initRecyclerView7() {
        int spanCount = 3;
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
//        builder.horColor(R.color.red);
//        builder.verColor(R.color.blue_00);
        builder.horSize(mSpace);
        builder.verSize(mSpace);
        builder.showLastDivider(false);
        recyclerview_7.addItemDecoration(builder.build());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerview_7.setLayoutManager(gridLayoutManager);
        //设置分割线 item居中显示
        Card_129_item_adapter mAdapter = new Card_129_item_adapter(this, R.layout.card_129_item, mImageWidth);
        recyclerview_7.setAdapter(mAdapter);
        List<String> list = new ArrayList<>();
        list.add(images[0]);
        list.add(images[1]);
        list.add(images[2]);
        list.add(images[3]);
        list.add(images[4]);
        list.add(images[5]);
        list.add(images[6]);
        mAdapter.setList(list);
    }
    /**
     * 初始化recyclerview
     */
    private void initRecyclerView8() {
        int spanCount = 3;
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
//        builder.horColor(R.color.red);
//        builder.verColor(R.color.blue_00);
        builder.horSize(mSpace);
        builder.verSize(mSpace);
        builder.showLastDivider(false);
        recyclerview_8.addItemDecoration(builder.build());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerview_8.setLayoutManager(gridLayoutManager);
        //设置分割线 item居中显示
        Card_129_item_adapter mAdapter = new Card_129_item_adapter(this, R.layout.card_129_item, mImageWidth);
        recyclerview_8.setAdapter(mAdapter);
        List<String> list = new ArrayList<>();
        list.add(images[0]);
        list.add(images[1]);
        list.add(images[2]);
        list.add(images[3]);
        list.add(images[4]);
        list.add(images[5]);
        list.add(images[6]);
        list.add(images[7]);
        mAdapter.setList(list);
    }
    /**
     * 初始化recyclerview
     */
    private void initRecyclerView9() {
        int spanCount = 3;
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
//        builder.horColor(R.color.red);
//        builder.verColor(R.color.blue_00);
        builder.horSize(mSpace);
        builder.verSize(mSpace);
        builder.showLastDivider(false);
        recyclerview_9.addItemDecoration(builder.build());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerview_9.setLayoutManager(gridLayoutManager);
        //设置分割线 item居中显示
        Card_129_item_adapter mAdapter = new Card_129_item_adapter(this, R.layout.card_129_item, mImageWidth);
        recyclerview_9.setAdapter(mAdapter);
        List<String> list = new ArrayList<>();
        list.add(images[0]);
        list.add(images[1]);
        list.add(images[2]);
        list.add(images[3]);
        list.add(images[4]);
        list.add(images[5]);
        list.add(images[6]);
        list.add(images[7]);
        list.add(images[8]);
        mAdapter.setList(list);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}