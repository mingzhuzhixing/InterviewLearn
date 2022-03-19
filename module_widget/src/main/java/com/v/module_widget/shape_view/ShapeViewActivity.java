package com.v.module_widget.shape_view;

import android.os.Message;
import android.view.View;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_handler.weakreference.BaseHandler;
import com.v.module_widget.R;

/**
 * 不同图形切换
 */
public class ShapeViewActivity extends BaseTitleBarActivity {

    private ShapeView shapeView;


    private final MyHandler myHandler = new MyHandler(this);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shape_view;
    }

    @Override
    protected void initData() {
        shapeView = findViewById(R.id.shape_view);
    }

    @Override
    protected String setTitle() {
        return "变换不同的图形";
    }

    @Override
    protected void processLogical() {

    }

    public void startClick(View view) {
        myHandler.sendEmptyMessage(1);
    }

    public static class MyHandler extends BaseHandler<ShapeViewActivity> {

        public MyHandler(ShapeViewActivity shapeViewActivity) {
            super(shapeViewActivity);
        }

        @Override
        public void handleMessages(ShapeViewActivity shapeViewActivity, Message msg) {
            shapeViewActivity.shapeView.exchange();
            shapeViewActivity.myHandler.sendEmptyMessageDelayed(1, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.release();
    }
}
