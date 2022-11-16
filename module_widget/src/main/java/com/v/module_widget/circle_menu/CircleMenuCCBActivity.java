package com.v.module_widget.circle_menu;

import android.view.View;
import android.widget.Toast;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

/**
 * 建设银行 圆形菜单
 */
public class CircleMenuCCBActivity extends BaseTitleBarActivity {

    private String[] mItemTexts = new String[]{"安全中心 ", "特色服务", "投资理财", "转账汇款", "我的账户", "信用卡"};
    private int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal, R.drawable.home_mbank_2_normal,
            R.drawable.home_mbank_3_normal, R.drawable.home_mbank_4_normal,
            R.drawable.home_mbank_5_normal, R.drawable.home_mbank_6_normal};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle_menu_ccb;
    }

    @Override
    protected String setTitle() {
        return "建设银行 圆形菜单";
    }

    @Override
    protected void initData() {
        CircleMenuCCBLayout mCircleMenuLayout = findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuCCBLayout.OnMenuItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {
                Toast.makeText(CircleMenuCCBActivity.this, mItemTexts[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void itemCenterClick(View view) {
                Toast.makeText(CircleMenuCCBActivity.this, "you can do something just like ccb  ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void processLogical() {

    }
}