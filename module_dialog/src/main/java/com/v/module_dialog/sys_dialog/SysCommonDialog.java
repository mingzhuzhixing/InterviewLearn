package com.v.module_dialog.sys_dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.makeramen.roundedimageview.RoundedImageView;
import com.v.module_dialog.R;
import com.v.module_dialog.dialog.base.BaseDialog;
import com.v.module_glide.GlideUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author wangbq
 * @name bookshare
 * @class name：com.fengwo.function.mqtt.view
 * @class describe
 * @time 2020-04-10 14:51
 */
public class SysCommonDialog extends BaseDialog implements View.OnClickListener {
    ImageView ivImage;
    TextView tvTitle;
    TextView tvContent;
    TextView tvOk, tvOkVertical;
    TextView tvCancel, tvCancelVertical;
    ImageView ivClose;
    RelativeLayout rootLayout;

    RelativeLayout body_layout;
    LinearLayout buttonLayout, buttonLayoutVertical;
    RoundedImageView rvTopBigImage;

    private boolean isCanCancle = true;

    private Context mContext;
    private ClickListener onclick;

    /**
     * @hide
     */
    @IntDef({HORIZONTAL, VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private boolean btnOkClickDismiss = true;


    public SysCommonDialog(@NonNull Context context) {
        this(context, R.style.CommonDialogStyle);
        mContext = context;
        try {
            this.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SysCommonDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        if (null != getWindow()) {
            getWindow().setWindowAnimations(R.style.dialog_animation);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_sys_common, null, true);
        ivImage = view.findViewById(R.id.iv_image);
        tvTitle = view.findViewById(R.id.tv_title);
        tvContent = view.findViewById(R.id.tv_content);
        tvOk = view.findViewById(R.id.tv_ok);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvOkVertical = view.findViewById(R.id.tv_ok_vertical);
        tvCancelVertical = view.findViewById(R.id.tv_cancel_vertical);
        ivClose = view.findViewById(R.id.iv_close);
        rootLayout = view.findViewById(R.id.root_layout);
        body_layout = view.findViewById(R.id.body_layout);
        buttonLayout = view.findViewById(R.id.button_layout);
        buttonLayoutVertical = view.findViewById(R.id.button_layout_vertical);
        rvTopBigImage = view.findViewById(R.id.rv_top_big_image);
        tvOk.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvOkVertical.setOnClickListener(this);
        tvCancelVertical.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        rootLayout.setOnClickListener(this);
        setContentView(view);
        initWindowAttribute();
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    public SysCommonDialog setCloseIconVisibility(int v) {
        ivClose.setVisibility(v);
        return this;
    }

    public SysCommonDialog setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.getPaint().setFakeBoldText(true);
        }
        return this;
    }

    public SysCommonDialog setMovementMethod(MovementMethod movement) {
        tvContent.setMovementMethod(movement);
        return this;
    }

    /**
     * 多行文案处理
     */
    public SysCommonDialog append(CharSequence text) {
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setMaxLines(Integer.MAX_VALUE);
        tvContent.setEllipsize(null);
        tvContent.setGravity(Gravity.LEFT);
        tvContent.append(text);
        return this;
    }


    public SysCommonDialog setContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            tvContent.setText(content);
            tvContent.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public SysCommonDialog setContentMargin(int left, int top, int right, int bottom) {
        try {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tvContent.getLayoutParams();
            lp.setMargins(left, top, right, bottom);
            tvContent.setLayoutParams(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置内容没有标题
     * 注意: 配合字体大小和字体颜色一起使用
     */
    public SysCommonDialog setContentNoTitle(String content) {
        if (!TextUtils.isEmpty(content)) {
            try {
                tvContent.setText(content);
                tvContent.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.GONE);
                setContentColor("#1B1B1B");
                tvContent.setLineSpacing(21.0f, 1.0f);
                setContentSize(mContext.getResources().getDimension(R.dimen.y30));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 设置内容没有标题
     */
    public SysCommonDialog setContentNoTitle(CharSequence content) {
        if (!TextUtils.isEmpty(content)) {
            try {
                tvContent.setText(content);
                tvContent.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.GONE);
                setContentColor("#1B1B1B");
                tvContent.setLineSpacing(21.0f, 1.0f);
                setContentSize(mContext.getResources().getDimension(R.dimen.y30));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 设置按钮的排列方式
     * 默认是横向排列
     */
    public SysCommonDialog setButtonOrientation(@OrientationMode int orientation) {
        try {
            if (orientation == VERTICAL) {
                //竖向
                buttonLayout.setVisibility(View.GONE);
                buttonLayoutVertical.setVisibility(View.VISIBLE);
            } else {
                //横向
                buttonLayout.setVisibility(View.VISIBLE);
                buttonLayoutVertical.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置顶部大图
     */
    public SysCommonDialog setTopBigImage(String url) {
        if (!TextUtils.isEmpty(url)) {
            try {
                GlideUtils.loadImage(mContext, url, rvTopBigImage, -1, 0);
                rvTopBigImage.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 设置顶部大图
     */
    public SysCommonDialog setTopBigImage(int resId) {
        if (resId > 0) {
            try {
                rvTopBigImage.setImageResource(resId);
                rvTopBigImage.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public SysCommonDialog setImage(String url) {
        if (!TextUtils.isEmpty(url)) {
            try {
                GlideUtils.loadImage(mContext, url, ivImage, -1, 0);
                ivImage.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public SysCommonDialog setImage(int resId) {
        if (resId > 0) {
            try {
                ivImage.setImageResource(resId);
                ivImage.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

//    public SysCommonDialog setActionLog(ActionLog actionLog) {
//        this.actionLog = actionLog;
//        return this;
//    }

    public SysCommonDialog setOnclick(ClickListener onclick) {
        this.onclick = onclick;
        return this;
    }

    public SysCommonDialog setScheme(String scheme) {
        try {
            if (!TextUtils.isEmpty(scheme)) {
                this.tvOk.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public SysCommonDialog setButtonStr(@Nullable String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.tvOk.setText(str);
                this.tvOkVertical.setText(str);
            }
            this.tvOk.setVisibility(View.VISIBLE);
            this.tvOkVertical.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public SysCommonDialog setButtonCancelStr(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                this.tvCancel.setText(str);
                this.tvCancel.setVisibility(View.VISIBLE);
                this.tvCancelVertical.setText(str);
                this.tvCancelVertical.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.tvCancel.setVisibility(View.VISIBLE);
        return this;
    }


    public SysCommonDialog setCanOnTouch(boolean isCan) {
        this.setCanceledOnTouchOutside(isCan);
        this.setCancelable(isCan);
        isCanCancle = false;
        return this;
    }

    public SysCommonDialog setImageStyle(int type) {
        try {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.x390);
            layoutParams.height = (int) mContext.getResources().getDimension(R.dimen.x300);
            //layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            ivImage.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public SysCommonDialog setContentColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            tvContent.setTextColor(Color.parseColor(color));
        }
        return this;
    }

    /**
     * 设置内容字体大小
     *
     * @param size getResources().getDimension(R.dimen.y22)
     */
    public SysCommonDialog setContentSize(float size) {
        if (size > 0) {
            try {
                tvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public SysCommonDialog setButtonTextColor(String color) {
        if (!TextUtils.isEmpty(color)) {
            try {
                tvOk.setTextColor(Color.parseColor(color));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public SysCommonDialog setButtonBgColor(int res) {
        if (res != 0) {
            try {
                tvOk.setBackgroundResource(res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public SysCommonDialog setButtonEnable(boolean enable) {
        try {
            tvOk.setEnabled(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public SysCommonDialog addBodyView(BodyView bodyView) {
        if (bodyView != null) {
            body_layout.addView(bodyView.bodyView());
        }
        return this;
    }

    public SysCommonDialog cancelBtnOkClickDismiss() {
        btnOkClickDismiss = false;
        return this;
    }

    public interface BodyView {
        View bodyView();
    }

    public static final int BTN_CLOSE = 1;
    public static final int BTN_LAYOUT = 2;
    public static final int BTN_OK = 3;
    public static final int BTN_CANCEL = 4;
    public static final int NELL = 0;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({BTN_CLOSE, BTN_LAYOUT, BTN_OK, BTN_CANCEL, NELL})
    @interface DISMISSTYPE {
    }

    @DISMISSTYPE
    private int dismissType = NELL;

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_close) {
            try {
//                if (actionLog != null) {
//                    DataApiPresenter.addSensorsForOverallDialog("popupWindowAction", "close", actionLog.sded);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            dismissType = BTN_CLOSE;
            dismiss();
        } else if (id == R.id.tv_ok_vertical || id == R.id.tv_ok) {
            if (onclick != null) {
                onclick.onClick();
            }
            dismissType = BTN_OK;
            if (btnOkClickDismiss) dismiss();
        } else if (id == R.id.root_layout) {
            if (isCanCancle) {
                dismissType = BTN_LAYOUT;
                dismiss();
            }
        } else if (id == R.id.tv_cancel_vertical || id == R.id.tv_cancel) {
            if (onclick != null) {
                ((ClickTwoButtonlistener) onclick).onCancelClick();
            }
            dismissType = BTN_CANCEL;
            dismiss();
        }
    }


    public interface ClickListener {
        void onClick();

        void dismiss();
    }

    public interface ClickTwoButtonlistener extends ClickListener {
        void onCancelClick();
    }

    public interface ClickALlStateListener extends ClickTwoButtonlistener {
        void dismiss(int dismissType);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        try {
            if (onclick != null) {
                onclick.dismiss();
                if (onclick instanceof ClickALlStateListener) {
                    ((ClickALlStateListener) onclick).dismiss(dismissType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}