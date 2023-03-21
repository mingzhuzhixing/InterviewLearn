package com.v.module_recyclerview.expand_recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.v.module_recyclerview.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 一个可展开和收起的RecyclerView数据处理，传进的数据和显示的数据分开，展开添加item，收起则删除item。
 * Created by ldw on 2017/12/1.
 */

public class ExpandRecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    public static final int VIEW_TYPE_GROUP = 1;
    public static final int VIEW_TYPE_CHILD = 2;

    //传进来的课程信息
    private CourseInfo courseInfo;

    //显示的数据集
    private List<BaseInfo> dataInfos = new ArrayList<>();
    //当前展开的课时，-1代表没有任何展开
    private int curExpandChapterIndex = -1;

    public ExpandRecyclerViewAdapter(CourseInfo _courseInfo) {
        this.courseInfo = _courseInfo;
        for (BaseInfo info : courseInfo.chapterInfos) {
            dataInfos.add(info);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == VIEW_TYPE_GROUP) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false);
            return new GroupViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false);
            return new ChildViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == VIEW_TYPE_GROUP) {
            GroupViewHolder itemHolder = (GroupViewHolder) holder;
            itemHolder.itemView.setTag(position);
            itemHolder.tvPractise.setTag(position);

            GroupInfo chapterInfo = (GroupInfo) dataInfos.get(position);
            itemHolder.tvName.setText(chapterInfo.name);

            if (chapterInfo.sectionInfos.size() > 0) {
                itemHolder.ivArrow.setVisibility(View.VISIBLE);
                if (curExpandChapterIndex == position) {
                    itemHolder.ivArrow.setBackgroundResource(R.drawable.arrow_up);
                } else {
                    itemHolder.ivArrow.setBackgroundResource(R.drawable.arrow_down);
                }
            } else {
                itemHolder.ivArrow.setVisibility(View.INVISIBLE);
            }
        } else {
            ChildViewHolder itemSectionHolder = (ChildViewHolder) holder;
            itemSectionHolder.tvName.setTag(position);
            ChildInfo sectionInfo = (ChildInfo) dataInfos.get(position);
            itemSectionHolder.tvName.setText(sectionInfo.name);
        }
    }

    //该方法只更改itemView的部分信息，不全部刷新
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            String str = (String) payloads.get(0);
            //更改view的tag
            if (str.equals("change_position")) {
                if (getItemViewType(position) == VIEW_TYPE_GROUP) {
                    GroupViewHolder itemHolder = (GroupViewHolder) holder;
                    itemHolder.itemView.setTag(position);
                    itemHolder.tvPractise.setTag(position);
                    //改变箭头方向
                    if (curExpandChapterIndex == position) {
                        itemHolder.ivArrow.setBackgroundResource(R.drawable.arrow_up);
                    } else {
                        itemHolder.ivArrow.setBackgroundResource(R.drawable.arrow_down);
                    }
                } else {
                    ChildViewHolder itemSectionHolder = (ChildViewHolder) holder;
                    itemSectionHolder.tvName.setTag(position);
                }
            }
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        if (dataInfos == null) {
            return 0;
        } else {
            return dataInfos.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (dataInfos.get(position) instanceof GroupInfo) {
            return VIEW_TYPE_GROUP;
        } else if (dataInfos.get(position) instanceof ChildInfo) {
            return VIEW_TYPE_CHILD;
        }
        return super.getItemViewType(position);
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivArrow;
        public TextView tvName;
        public TextView tvPractise;

        public GroupViewHolder(View itemView) {
            super(itemView);
            ivArrow = itemView.findViewById(R.id.iv_item_chapter_arrow);
            tvName = itemView.findViewById(R.id.tv_item_chapter_name);
            tvPractise = itemView.findViewById(R.id.tv_item_chapter_practise);

            //将创建的View注册点击事件
            itemView.setOnClickListener(ExpandRecyclerViewAdapter.this);
            tvPractise.setOnClickListener(ExpandRecyclerViewAdapter.this);
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ChildViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_section_name);

            //将创建的View注册点击事件
            tvName.setOnClickListener(ExpandRecyclerViewAdapter.this);
        }
    }


    //以下为item点击处理
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * item里面有多个控件可以点击
     */
    public enum ViewName {
        CHAPTER_ITEM,
        CHAPTER_ITEM_PRACTISE,
        SECTION_ITEM
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int chapterIndex, int sectionIndex);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            int position = (int) v.getTag();
            ViewName viewName = ViewName.CHAPTER_ITEM;
            int chapterIndex = -1;
            int sectionIndex = -1;
            if (getItemViewType(position) == VIEW_TYPE_GROUP) {
                GroupInfo chapterInfo = (GroupInfo) dataInfos.get(position);
                chapterIndex = chapterInfo.chapterIndex;
                sectionIndex = -1;
                if (v.getId() == R.id.tv_item_chapter_practise) {
                    viewName = ViewName.CHAPTER_ITEM_PRACTISE;
                } else {
                    viewName = ViewName.CHAPTER_ITEM;
                    if (chapterInfo.sectionInfos.size() > 0) {
                        if (chapterIndex == curExpandChapterIndex) {
                            narrow(curExpandChapterIndex);
                        } else {
                            narrow(curExpandChapterIndex);
                            expand(chapterIndex);
                        }
                    }
                }
            } else if (getItemViewType(position) == VIEW_TYPE_CHILD) {
                ChildInfo sectionInfo = (ChildInfo) dataInfos.get(position);
                viewName = ViewName.SECTION_ITEM;
                chapterIndex = sectionInfo.chapterIndex;
                sectionIndex = sectionInfo.sectionIndex;
            }
            mOnItemClickListener.onClick(v, viewName, chapterIndex, sectionIndex);
        }
    }

    /**
     * 展开某个item
     */
    private void expand(int chapterIndex) {
        dataInfos.addAll(chapterIndex + 1, courseInfo.chapterInfos.get(chapterIndex).sectionInfos);
        curExpandChapterIndex = chapterIndex;
        Log.v("zm1234","---expand---" + (chapterIndex + 1) + ", " + courseInfo.chapterInfos.get(chapterIndex).sectionInfos.size());
        notifyItemRangeInserted(chapterIndex + 1, courseInfo.chapterInfos.get(chapterIndex).sectionInfos.size());
        /*notifyItemRangeChanged(chapterIndex + 1 + courseInfo.chapterInfos.get(chapterIndex).sectionInfos.size(),
                getItemCount() - chapterIndex - 1, "change_position");*/
        notifyItemRangeChanged(0, getItemCount(), "change_position");
    }

    /**
     * 收起某个item
     */
    private void narrow(int chapterIndex) {
        if (chapterIndex != -1) {
            int removeStart = chapterIndex + 1;
            int removeCount = 0;
            for (int i = removeStart; i < dataInfos.size() && getItemViewType(i) == VIEW_TYPE_CHILD; i++) {
                removeCount++;
            }
            dataInfos.removeAll(courseInfo.chapterInfos.get(chapterIndex).sectionInfos);
            curExpandChapterIndex = -1;
            Log.v("zm1234","---narrow---" + removeStart + ", " + removeCount);
            notifyItemRangeRemoved(removeStart, removeCount);

            //notifyItemRangeChanged(removeStart, getItemCount() - removeStart, "change_position");
            notifyItemRangeChanged(0, getItemCount(), "change_position");
        }
    }
}
