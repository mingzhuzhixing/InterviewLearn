package com.v.ioc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.v.interviewlearn.R;
import com.v.ioc.annotation.ContentView;
import com.v.ioc.annotation.InjectView;
import com.v.ioc.annotation.OnClick;
import com.v.ioc.annotation.OnLongClick;

@ContentView(R.layout.activity_ioc)
public class IocActivity extends BaseActivity {
    @InjectView(R.id.button1)
    Button button;

    @InjectView(R.id.button2)
    Button button2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("zm", "--1--" + button.toString());
        LogUtils.e("zm", "--2--" + button2.toString());
    }

    @OnClick({R.id.button1, R.id.button2})
    public void onClick(View v) {
        Toast.makeText(this,"按下了",Toast.LENGTH_SHORT).show();
    }

    @OnLongClick({R.id.button2})
    public boolean onLongClick(View v) {
        Toast.makeText(this,"onLongClick",Toast.LENGTH_SHORT).show();
        return true;
    }
}
