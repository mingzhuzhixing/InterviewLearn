package com.v.dialog_module.popup;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.v.dialog_module.R;
import com.v.dialog_module.toast.ToastUtils;

public class CustomPop extends BasePopDialog {

    public CustomPop(Context context) {
        super(context);
    }

    @Override
    public int getViewResId() {
        return R.layout.view_pop_custom;
    }

    @Override
    public void initData(View contentView) {
        TextView tv_pop = (TextView) contentView.findViewById(R.id.tv_pop);
        tv_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showRoundRectToast("滚犊子吧");
            }
        });
    }
}