package com.v.module_widget.like_view;

import android.view.View;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

/**
 * 直播点赞动效
 */
public class KsgLikeViewActivity extends BaseTitleBarActivity {
    private KsgLikeView mKsgLikeView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ksg_like_view;
    }

    @Override
    protected String setTitle() {
        return "直播点赞动效";
    }

    @Override
    public void initData() {
        mKsgLikeView = findViewById(R.id.live_view);
        mKsgLikeView.addLikeImages(
                R.mipmap.ic_live_yellow_heart, R.mipmap.ic_live_smile_face_heart, R.mipmap.ic_live_red_heart,
                R.mipmap.ic_live_thumbs_up_yellow, R.mipmap.ic_live_smile_face, R.mipmap.ic_live_orange_heart,
                R.mipmap.ic_live_grin_face_eyes, R.mipmap.ic_live_smile_face_sunglas);
    }

    /**
     * 点击飘心
     */
    public void likeViewClick(View view) {
        if (mKsgLikeView != null) {
            mKsgLikeView.addFavor();
        }
    }
}