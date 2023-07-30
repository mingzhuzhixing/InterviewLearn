package com.v.module_widget.circle_menu;

import android.view.View;
import android.widget.Toast;
import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

/**
 * 建设银行 圆形菜单
 */
public class CircleMenuActivity extends BaseTitleBarActivity {
    private final String[] mItemTexts = new String[]{"安全中心 "};
    private final int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal};
    private CircleMenuLayout mCircleMenuLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle_menu;
    }

    @Override
    protected String setTitle() {
        return "圆形菜单";
    }

    @Override
    public void initData() {
        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts, -180);

        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {
                Toast.makeText(CircleMenuActivity.this, mItemTexts[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void itemCenterClick(View view) {
                Toast.makeText(CircleMenuActivity.this, "you can do something just like ccb  ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void show1Click(View view) {
        mCircleMenuLayout.removeAllChildView();
        String[] mItemTexts = new String[]{"安全中心 "};
        int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal};
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts, -180);
    }

    public void show2Click(View view) {
        mCircleMenuLayout.removeAllChildView();
        String[] mItemTexts = new String[]{"安全中心 ", "特色服务"};
        int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal, R.drawable.home_mbank_2_normal};
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts, -180);
    }

    public void show3Click(View view) {
        mCircleMenuLayout.removeAllChildView();
        String[] mItemTexts = new String[]{"安全中心 ", "特色服务", "投资理财"};
        int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal, R.drawable.home_mbank_2_normal,
                R.drawable.home_mbank_3_normal};
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts, -90);
    }

    public void show4Click(View view) {
        mCircleMenuLayout.removeAllChildView();
        String[] mItemTexts = new String[]{"安全中心 ", "特色服务", "投资理财", "转账汇款"};
        int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal, R.drawable.home_mbank_2_normal,
                R.drawable.home_mbank_3_normal, R.drawable.home_mbank_4_normal};
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts, -135);
    }

    public void show5Click(View view) {
        mCircleMenuLayout.removeAllChildView();
        String[] mItemTexts = new String[]{"安全中心 ", "特色服务", "投资理财", "转账汇款", "我的账户"};
        int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal, R.drawable.home_mbank_2_normal,
                R.drawable.home_mbank_3_normal, R.drawable.home_mbank_4_normal,
                R.drawable.home_mbank_5_normal};
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts, -90);
    }

    public void show6Click(View view) {
        mCircleMenuLayout.removeAllChildView();
        String[] mItemTexts = new String[]{"安全中心 ", "特色服务", "投资理财", "转账汇款", "我的账户", "信用卡"};
        int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal, R.drawable.home_mbank_2_normal,
                R.drawable.home_mbank_3_normal, R.drawable.home_mbank_4_normal,
                R.drawable.home_mbank_5_normal, R.drawable.home_mbank_6_normal};
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts, -180);
    }
}