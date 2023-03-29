package com.v.module_textview.ellipsize_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ClassName: EllipsizeLayout
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_textview.ellipsize_view
 * @date 2023/3/29 19:41
 */
public class EllipsizeLayout extends LinearLayout {

    public EllipsizeLayout(Context context) {
        this(context, null);
    }

    public EllipsizeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getOrientation() == HORIZONTAL
                && (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY)) {
            int totalLength = 0;
            boolean outOfSpec = false;
            TextView ellipView = null;
            final int count = getChildCount();

            for (int ii = 0; ii < count && !outOfSpec; ++ii) {
                final View child = getChildAt(ii);
                if (child != null && child.getVisibility() != GONE) {
                    if (child instanceof TextView) {
                        final TextView tv = (TextView) child;
                        if (tv.getEllipsize() != null) {
                            if (ellipView == null) {
                                ellipView = tv;
                                // clear maxWidth on mEllipView before measure
                                ellipView.setMaxWidth(Integer.MAX_VALUE);
                            } else {
                                // TODO: support multiple android:ellipsize
                                outOfSpec = true;
                            }
                        }
                    }
                    final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child
                            .getLayoutParams();
                    outOfSpec |= (lp.weight > 0f);
                    measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                    totalLength += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                }
            }
            outOfSpec |= (ellipView == null) || (totalLength == 0);
            final int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

            if (!outOfSpec && totalLength > parentWidth) {
                int maxWidth = ellipView.getMeasuredWidth() - (totalLength - parentWidth);
                // TODO: Respect android:minWidth (easy with @TargetApi(16))
                int minWidth = 0;
                if (maxWidth < minWidth) {
                    maxWidth = minWidth;
                }
                ellipView.setMaxWidth(maxWidth);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
