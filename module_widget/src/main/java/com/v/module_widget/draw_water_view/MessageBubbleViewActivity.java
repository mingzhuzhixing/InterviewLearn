package com.v.module_widget.draw_water_view;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

public class MessageBubbleViewActivity extends BaseTitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_bubble_view;
    }

    @Override
    protected String setTitle() {
        return "消息气泡拖拽view";
    }

    @Override
    protected void initData() {
        TextView text = findViewById(R.id.tv_text);

        MessageBubbleView.attach(text, new BubbleMessageTouchListener.BubbleDisappearListener() {
            @Override
            public void dismiss(View view) {
                Toast.makeText(MessageBubbleViewActivity.this, "爆炸了，我消失了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void processLogical() {

    }
}