ckage com.v.module_widget.scoll_number;


import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;
import com.v.module_widget.scoll_number.library.MultiScrollNumber;

/**
 * 滚动数字控件ScrollNumber
 * https://github.com/a-voyager/ScrollNumber
 */
public class ScrollNumberActivity extends BaseTitleBarActivity {
    MultiScrollNumber scrollNumber;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_number;
    }

    @Override
    protected String setTitle() {
        return "滚动数字控件ScrollNumber";
    }

    @Override
    protected void initData() {
        super.initData();
        scrollNumber.setNumber(2048);
        scrollNumber.setTextColors(new int[]{R.color.blue01, R.color.red01,
                R.color.green01, R.color.purple01});
        scrollNumber.setTextSize(64);
        scrollNumber.setNumber(64, 2048);
        scrollNumber.setInterpolator(new DecelerateInterpolator());
        scrollNumber.setScrollVelocity(30);
    }

    public void addClick(View view) {
    }
}