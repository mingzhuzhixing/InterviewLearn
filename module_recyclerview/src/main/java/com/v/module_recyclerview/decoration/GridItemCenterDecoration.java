package com.v.module_recyclerview.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


/**
 * gridview 中item居中显示
 */
public class GridItemCenterDecoration extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int spacing;
    //是否包括四个边距
    private boolean includeEdge;
    private int size;

    public GridItemCenterDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }
    public GridItemCenterDecoration(int spanCount, int spacing, boolean includeEdge,int size) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.size = size;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        try {
            int position = parent.getChildAdapterPosition(view);
            //竖向
            int column = position % spanCount;
            //横向
            int   hang ;
            if(size<=spanCount){
                hang =1;
            }else {
                if(size % spanCount ==0){
                    hang = size /spanCount ;
                }else{
                    hang = size /spanCount + 1 ;
                }
            }

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;
                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if(hang ==1){
                    outRect.top = 0;
                    outRect.bottom = 0;
                }else if(hang ==2){
                    if(position < spanCount){
                        outRect.top = 0;
                        outRect.bottom = 0;
                    }else{
                        outRect.top = spacing;
                        outRect.bottom = 0;
                    }
                }else{
                    if(position < spanCount){
                        outRect.top = 0;
                        outRect.bottom = 0;
                    }else if((position/4) +1 == hang){
                        outRect.top = 0;
                        outRect.bottom = 0;
                    }else{
                        outRect.top = spacing;
                        outRect.bottom = spacing;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
