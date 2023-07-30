package com.v.module_recyclerview.expand_recyclerview;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_recyclerview.R;
import com.v.module_recyclerview.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 自定义Adapter实现RecyclerView的可展开二级列表expand效果
 */
public class ExpandRecyclerViewActivity extends BaseTitleBarActivity {
    @BindView(R2.id.recyclerview)
    public RecyclerView mRecyclerView;

    private Unbinder unbinder;

    private ExpandRecyclerViewAdapter ExpandRecyclerViewAdapter;
    private CourseInfo mCourseInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_expand_recyclerview;
    }

    @Override
    protected String setTitle() {
        return "可展开二级列表expand效果";
    }

    @Override
    public void initData() {
        super.initData();
        unbinder = ButterKnife.bind(this);

        initRecyclerViewData();
        initRecyclerView();
    }

    private void initRecyclerViewData(){
        //假数据
        mCourseInfo = new CourseInfo();
        mCourseInfo.name = "假装是课程的名称";
        for (int i = 0; i < 31; i++) {
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.name = "假装是课时名称" + (i + 1);
            groupInfo.chapterIndex = i;
            if (i == 0) {
                for (int j = 0; j < 2; j++) {
                    ChildInfo childInfo = new ChildInfo();
                    childInfo.name = "第" + (j + 1) + "节";
                    childInfo.chapterIndex = i;
                    childInfo.sectionIndex = j;
                    groupInfo.sectionInfos.add(childInfo);
                }
            } else if (i == 1) {
                for (int j = 0; j < 3; j++) {
                    ChildInfo childInfo = new ChildInfo();
                    childInfo.name = "第" + (j + 1) + "节";
                    childInfo.chapterIndex = i;
                    childInfo.sectionIndex = j;
                    groupInfo.sectionInfos.add(childInfo);
                }
            } else if (i == 2) {

            } else {
                for (int j = 0; j < 10; j++) {
                    ChildInfo childInfo = new ChildInfo();
                    childInfo.name = "第" + (j + 1) + "节";
                    childInfo.chapterIndex = i;
                    childInfo.sectionIndex = j;
                    groupInfo.sectionInfos.add(childInfo);
                }
            }
            mCourseInfo.chapterInfos.add(groupInfo);
        }
    }

    private void initRecyclerView(){
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExpandRecyclerViewAdapter ExpandRecyclerViewAdapter = new ExpandRecyclerViewAdapter(mCourseInfo);
        mRecyclerView.setAdapter(ExpandRecyclerViewAdapter);
        ExpandRecyclerViewAdapter.setOnItemClickListener(new ExpandRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, ExpandRecyclerViewAdapter.ViewName viewName, int chapterIndex, int sectionIndex) {
                //Timber.v("---onClick---"+viewName+", "+chapterIndex+", "+sectionIndex);
                switch (viewName){
                    case CHAPTER_ITEM:
                        if(mCourseInfo.chapterInfos.get(chapterIndex).sectionInfos.size() > 0){
                            Log.v("zm1234","---onClick---just expand or narrow: "+chapterIndex);
                            if(chapterIndex + 1 == mCourseInfo.chapterInfos.size()){
                                //如果是最后一个，则滚动到展开的最后一个item
                                mRecyclerView.smoothScrollToPosition(ExpandRecyclerViewAdapter.getItemCount());
                                Log.v("zm1234","---onClick---scroll to bottom");
                            }
                        }else{
                            onClickGroup(chapterIndex);
                        }
                        break;
                    case CHAPTER_ITEM_PRACTISE:
                        onClickPractise(chapterIndex);
                        break;
                    case SECTION_ITEM:
                        onClickChild(chapterIndex, sectionIndex);
                        break;
                }
            }
        });

        //以下是对布局进行控制，让组占据一行，孩子每四个占据一行，结果就是相当于一个ListView嵌套GridView的效果。
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return ExpandRecyclerViewAdapter.getItemViewType(position) == ExpandRecyclerViewAdapter.VIEW_TYPE_GROUP ? 4 : 1;
            }
        });
        mRecyclerView.setLayoutManager(manager);
    }


    private void onClickGroup(int chapterIndex) {
        Log.v("zm1234","---onClick---play chapter: " + chapterIndex);
    }

    private void onClickChild(int chapterIndex, int sectionIndex) {
        Log.v("zm1234","---onClick---play---section: " + chapterIndex + ", " + sectionIndex);
    }

    private void onClickPractise(int chapterIndex) {
        Log.v("zm1234","---onClick---practise: " + chapterIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
