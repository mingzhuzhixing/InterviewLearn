package com.v.module_recyclerview.overscroll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.TranslateAnimation;

import com.v.module_recyclerview.R;
import com.v.module_recyclerview.R2;
import com.v.module_recyclerview.decoration.HorizontalDividerItemDecoration;
import com.v.module_recyclerview.decoration.VerticalDividerItemDecoration;
import com.v.module_recyclerview.listview.ListviewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;

/**
 * 带阻尼效果
 */
public class OverscrollRecyclerViewActivity extends AppCompatActivity {
    @BindView(R2.id.recyclerview)
    public RecyclerView recyclerView;

    @BindView(R2.id.recyclerview2)
    public RecyclerView recyclerView2;

    @BindView(R2.id.recyclerview3)
    public RecyclerView recyclerView3;

    private ListviewAdapter mAdapter;
    private ListviewAdapter mAdapter2;
    private ListviewAdapter mAdapter3;
    private Unbinder unbinder;

    RecyclerView.ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overscroll_recycler_view);
        unbinder = ButterKnife.bind(this);

        //设置分割线
        itemDecoration = new VerticalDividerItemDecoration.Builder(this)
                .colorResId(R.color.color_4bba95)
                .sizeResId(R.dimen.y2)
                .build();

        initRecyclerView();
        initRecyclerView2();
        initRecyclerView3();
        initRecyclerView4();

        initData();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);
        mAdapter = new ListviewAdapter(R.layout.item_listview_hor);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int itemCount, firstPosition, lastPosition, lastItemCount;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    itemCount = layoutManager.getItemCount();
                    lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                    firstPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                } else {
                    Log.e("OverscrollR", "The OnLoadMoreListener only support LinearLayoutManager");
                    return;
                }
                if (firstPosition == 0) {
                    Log.e("OverscrollR", "The OnLoadMoreListener onRefresh（）");
                    TranslateAnimation anim = new TranslateAnimation(recyclerView.getLeft(), 100, 0, 0);
                    anim.setDuration(300);
                    recyclerView.startAnimation(anim);
                } else if (lastItemCount != itemCount && lastPosition == itemCount - 1) {
                    //lastItemCount = itemCount;
                    Log.e("OverscrollR", "The OnLoadMoreListener oonLoadMore（）");
                    TranslateAnimation anim = new TranslateAnimation(0, -100, 0, 0);
                    anim.setDuration(300);
                    recyclerView.startAnimation(anim);
                }
                Log.i("OverscrollR", "onScrollStateChanged:" + newState);
                switch (newState) {
                    /*滑动停止*/
                    case RecyclerView.SCROLL_STATE_IDLE:
                        break;
                    /*正在拖拽*/
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        break;
                    /*惯性滑动中*/
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("OverscrollR", "onScrolled:" + dx);
            }
        });

        // Horizontal
        //OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
//        new HorizontalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));
        new HorizontalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView), 1f, 1f, -5f);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView2() {
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.addItemDecoration(itemDecoration);
        mAdapter2 = new ListviewAdapter(R.layout.item_listview_hor);
        recyclerView2.setAdapter(mAdapter2);
    }


    /**
     * 初始化recyclerview
     */
    private void initRecyclerView3() {
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.addItemDecoration(itemDecoration);
        mAdapter3 = new ListviewAdapter(R.layout.item_listview_hor);
        recyclerView3.setAdapter(mAdapter3);
    }


    /**
     * 初始化recyclerview
     */
    private void initRecyclerView4() {
    }


    /**
     * 初始化数据
     */
    private void initData() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add("item" + i);
        }

        mAdapter.setList(mList);
        mAdapter2.setList(mList);
        mAdapter3.setList(mList);
    }
}