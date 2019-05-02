package com.v.property_optimizate.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.v.interviewlearn.R;

import java.util.ArrayList;
import java.util.List;

public class DroidCardsView extends View {
    /**
     * 图片与图片之间的间距
     */
    private int mCardSpacing=150;

    /**
     * 图片与做测距离的记录
     */
    private int mCardLeft=10;

    private List<DroidCard> mDroidCards=new ArrayList<>();

    private Paint paint=new Paint();


    public DroidCardsView(Context context) {
        super(context);
        initCards();
    }

    public DroidCardsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCards();
    }

    protected void initCards(){
        Resources res=getResources();

        mDroidCards.add(new DroidCard(res, R.drawable.ic_launcher_background,mCardLeft));

        mCardLeft+=mCardSpacing;
        mDroidCards.add(new DroidCard(res, R.drawable.ic_launcher_background,mCardLeft));

        mCardLeft+=mCardSpacing;
        mDroidCards.add(new DroidCard(res, R.drawable.ic_launcher_background,mCardLeft));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i =0; i<mDroidCards.size()-1; i++){
            drawDroidCard(canvas,mDroidCards,i);
        }

        drawLastDroidCard(canvas,mDroidCards.get(mDroidCards.size()));

        invalidate();
    }

    /**
     * 绘制最后一个图片
     * @param canvas
     * @param c
     */
    private void drawLastDroidCard(Canvas canvas,DroidCard c){
        //canvas.drawBitmap(c.bitmap,c.x, 0f, paint);
    }

    /**
     * 绘制
     * @param canvas
     * @param droidCards
     * @param i
     */
    private void drawDroidCard(Canvas canvas,List<DroidCard> droidCards,int i){

        DroidCard c=droidCards.get(i);
        canvas.save();

        //剪辑
        //canvas.clipRect(c.x,0f,(float) mDroidCards.get(i+1).x,(float)c.height);
        //canvas.drawBitmap(c.bitmap,c.x,paint);
        canvas.restore();
    }
}
