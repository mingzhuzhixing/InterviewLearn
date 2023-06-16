package com.v.module_optimize.ui;

import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_optimize.R;

/**
 * view stub
 *
 * 使用简介参考
 * https://www.cnblogs.com/lianghe01/p/5712393.html
 * https://blog.csdn.net/a740169405/article/details/50351013
 */
public class ViewStubActivity extends BaseTitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_stub;
    }

    @Override
    protected String setTitle() {
        return "ViewStub的使用与详情";
    }

    ViewStub viewStub1;
    Button viewStub1Btn;

    @Override
    protected void processLogical() {
        super.processLogical();
        viewStub1 = findViewById(R.id.view_stub_1);
    }

    /**
     * 测试显示与隐藏
     */
    public void viewStubClick(View view) {
        if (viewStub1.getVisibility() == View.VISIBLE) {
            viewStub1.setVisibility(View.GONE);
        } else {
            viewStub1.setVisibility(View.VISIBLE);
        }

        viewStub1Btn = findViewById(R.id.view_stub_1_btn);
        viewStub1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewStubActivity.this, "点击了view_stub_1中的按钮", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 调用inflate
     */
    View noDataView;

    public void viewStubInflateClick(View view) {
        if (noDataView == null) {
            ViewStub viewStub2 = findViewById(R.id.view_stub_2);
            noDataView = viewStub2.inflate();
        } else {
            if (noDataView.getVisibility() == View.VISIBLE) {
                noDataView.setVisibility(View.GONE);
            } else {
                noDataView.setVisibility(View.VISIBLE);
            }
        }

        Button button = findViewById(R.id.view_stub_2_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewStubActivity.this, "点击了view_stub_2中的按钮", Toast.LENGTH_LONG).show();
            }
        });
    }
}