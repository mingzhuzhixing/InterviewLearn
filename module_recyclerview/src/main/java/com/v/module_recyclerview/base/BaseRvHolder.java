package com.v.module_recyclerview.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.v.module_glide.GlideUtils;
import com.v.module_recyclerview.R;


/**
 * ClassName: BaseRvHolder
 * Description:
 */
public class BaseRvHolder extends RecyclerView.ViewHolder {
    private Context mCtx;
    private SparseArray<View> mViewArray = new SparseArray<>();

    /**
     * 创建ViewHolder实例
     */
    public static BaseRvHolder createViewHolder(Context ctx, View itemView) {
        return new BaseRvHolder(ctx, itemView);
    }

    /**
     * 方法用private，私有化构造方法，限制只允许使用SuperRecyclerHolder.createViewHolder()来创建实例
     */
    public BaseRvHolder(Context ctx, View itemView) {
        super(itemView);
        mCtx = ctx;
    }

    public Context getContext() {
        return mCtx;
    }

    public View getItemView() {
        return itemView;
    }

    public View getViewById(@IdRes int viewId) {
        return retrieveView(viewId);
    }

    /**
     * 整个item的点击事件
     */
    public BaseRvHolder setOnItemClickListenner(@Nullable View.OnClickListener listener) {
        getItemView().setOnClickListener(listener);
        return this;
    }

    /**
     * 整个item的点击事件，可以根据条件来禁止某些符合条件的点击事件
     * <p>
     * 这个需求是在是开发的时候发现的，才加上去的，能不能点击根据后端的返回来确定的，可以使用该方法
     * 比如：列表显示了很多好友的用户名，在线的可以点击，不在线的不能点击，
     */
    public BaseRvHolder setOnItemClickListenner(boolean isListener,
                                                @Nullable View.OnClickListener listener) {
        getItemView().setOnClickListener(isListener ? listener : null);
        return this;
    }

    /**
     * 整个item的长按事件
     */
    public BaseRvHolder setOnItemLongClickListener(
            @Nullable View.OnLongClickListener listener) {
        getItemView().setOnLongClickListener(listener);
        return this;
    }

    public BaseRvHolder setOnItemLongClickListener(boolean isListener,
                                                   @Nullable View.OnLongClickListener listener) {
        getItemView().setOnLongClickListener(isListener ? listener : null);
        return this;
    }

    /**
     * 整个item的触摸事件
     */
    public BaseRvHolder setOnItemTouchListener(@NonNull View.OnTouchListener listener) {
        getItemView().setOnTouchListener(listener);
        return this;
    }

    public BaseRvHolder setOnItemTouchListener(boolean isListener,
                                               @NonNull View.OnTouchListener listener) {
        getItemView().setOnTouchListener(isListener ? listener : null);
        return this;
    }

    public BaseRvHolder setOnClickListenner(@IdRes int viewId,
                                            @Nullable View.OnClickListener listener) {
        retrieveView(viewId).setOnClickListener(listener);
        return this;
    }

    public BaseRvHolder setOnLongClickListener(@IdRes int viewId,
                                               @Nullable View.OnLongClickListener listener) {
        retrieveView(viewId).setOnLongClickListener(listener);
        return this;
    }

    public BaseRvHolder setOnTouchListener(@IdRes int viewId,
                                           @NonNull View.OnTouchListener listener) {
        retrieveView(viewId).setOnTouchListener(listener);
        return this;
    }

    public BaseRvHolder setOnFocusChangeListener(@IdRes int viewId,
                                                 @NonNull View.OnFocusChangeListener listener) {
        retrieveView(viewId).setOnFocusChangeListener(listener);
        return this;
    }

    public BaseRvHolder setOnFocusChangeListener(
            @NonNull View.OnFocusChangeListener listener) {
        getItemView().setOnFocusChangeListener(listener);
        return this;
    }

    public BaseRvHolder setAlpha(@IdRes int viewId,
                                 @FloatRange(from = 0.0, to = 1.0) float alpha) {
        retrieveView(viewId).setAlpha(alpha);
        return this;
    }

    public BaseRvHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int resId) {
        retrieveView(viewId).setBackgroundResource(resId);
        return this;
    }

    public BaseRvHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        retrieveView(viewId).setBackgroundColor(color);
        return this;
    }

    public BaseRvHolder setClickable(@IdRes int viewId, boolean clickable) {
        retrieveView(viewId).setClickable(clickable);
        return this;
    }

    public BaseRvHolder setEnabled(@IdRes int viewId, boolean enabled) {
        retrieveView(viewId).setEnabled(enabled);
        return this;
    }

    public BaseRvHolder setFocusable(@IdRes int viewId, boolean focusable) {
        retrieveView(viewId).setFocusable(focusable);
        return this;
    }

    public BaseRvHolder setFocusableInTouchMode(@IdRes int viewId,
                                                boolean focusableInTouchMode) {
        retrieveView(viewId).setFocusableInTouchMode(focusableInTouchMode);
        return this;
    }

    public BaseRvHolder setTag(@IdRes int viewId, final Object tag) {
        retrieveView(viewId).setTag(tag);
        return this;
    }

    public BaseRvHolder setTag(@IdRes int viewId, int key, final Object tag) {
        retrieveView(viewId).setTag(key, tag);
        return this;
    }

    public BaseRvHolder setVisibility(@IdRes int viewId, int visibility) {
        retrieveView(viewId).setVisibility(visibility);
        return this;
    }

    /**
     * 传入是否显示，true显示，false Gone掉
     */
    public BaseRvHolder setVisibility(@IdRes int viewId, boolean isVisibility) {
        retrieveView(viewId).setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        return this;
    }

    public BaseRvHolder setLongClickable(@IdRes int viewId, boolean longClickable) {
        retrieveView(viewId).setLongClickable(longClickable);
        return this;
    }

    /**
     * AppCompatCheckBox, AppCompatCheckedTextView, AppCompatRadioButton,CheckBox, CheckedTextView,
     * CompoundButton, RadioButton, Switch, SwitchCompat, ToggleButton都可以使用
     * <p>
     * 最常使用的是CheckBox，RadioButton,SwitchCompat设置check
     */
    public BaseRvHolder setChecked(@IdRes int viewId, boolean checked) {
        Checkable iCheckable = retrieveView(viewId);
        iCheckable.setChecked(checked);
        return this;
    }

    /**
     * CheckBox, RadioButton, Switch, SwitchCompat, ToggleButton
     * ,AppCompatCheckBox, AppCompatRadioButton等都可以使用
     * 凡是继承CompoundButton的都可以使用
     * <p>
     * 最常使用的是CheckBox，RadioButton,SwitchCompat设置监听
     */
    public BaseRvHolder setOnCheckedChangeListener(@IdRes int viewId,
                                                   CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        CompoundButton checkBox = retrieveView(viewId);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        return this;
    }

    /**
     * 该方法使用频率非常高，而且大多时候，是从网络加载的数据，所有可能会出现空指针异常
     * StringUtils.obtainNoNullText转换以后，确保不会出现空指针异常，网络请求的数据不需要再次进行判空操作
     * <p>
     * 省去每次都要TextUtils.isEmpty操作，不用关心数据是否为空
     */
    public BaseRvHolder setText(@IdRes int viewId, String content) {
        return setText(viewId, content, "");
    }


    public BaseRvHolder setText(@IdRes int viewId, CharSequence content, TextView.BufferType type) {
        TextView textView = retrieveView(viewId);
        textView.setText(content, type);
        return this;
    }

    public BaseRvHolder setText(@IdRes int viewId, CharSequence content) {
        TextView textView = retrieveView(viewId);
        textView.setText(content);
        return this;
    }

    /**
     * 该方法使用频率非常高，而且大多时候，是从网络加载的数据，所有可能会出现空指针异常
     * StringUtils.obtainNoNullText转换以后，确保不会出现空指针异常，网络请求的数据不需要再次进行判空操作
     * <p>
     * 省去每次都要TextUtils.isEmpty操作，不用关心数据是否为空,可设置默认值
     */
    public BaseRvHolder setText(@IdRes int viewId, String content, String defaultContent) {
        TextView textView = retrieveView(viewId);
        textView.setText(obtainNoNullText(content, defaultContent));
        return this;
    }

    public BaseRvHolder setText(@IdRes int viewId, @StringRes int resId) {
        TextView textView = retrieveView(viewId);
        textView.setText(resId);
        return this;
    }

    /**
     * 设置字体加粗
     */
    public BaseRvHolder setFakeBoldText(@IdRes int viewId, boolean fakeBoldText) {
        TextView textView = retrieveView(viewId);
        textView.getPaint().setFakeBoldText(fakeBoldText);
        return this;
    }

    public BaseRvHolder setTextColor(@IdRes int viewId, @ColorInt int color) {
        TextView textView = retrieveView(viewId);
        textView.setTextColor(color);
        return this;
    }

    /**
     * 设置颜色，直接传入colorRes，在方法内部去转换
     */
    public BaseRvHolder setTextColorResource(@IdRes int viewId, @ColorRes int colorRes) {
        TextView textView = retrieveView(viewId);
        textView.setTextColor(ContextCompat.getColor(getContext(), colorRes));
        return this;
    }

    public BaseRvHolder setTextSize(@IdRes int viewId, float size) {
        TextView textView = retrieveView(viewId);
        textView.setTextSize(size);
        return this;
    }

    public BaseRvHolder setTextSize(@IdRes int viewId, int unit, float size) {
        TextView textView = retrieveView(viewId);
        textView.setTextSize(unit, size);
        return this;
    }

    public BaseRvHolder setMaxLines(@IdRes int viewId, int maxLines) {
        TextView textView = retrieveView(viewId);
        textView.setMaxLines(maxLines);
        return this;
    }

    public BaseRvHolder setInputType(@IdRes int viewId, int type) {
        TextView textView = retrieveView(viewId);
        textView.setInputType(type);
        return this;
    }

    public BaseRvHolder setHint(@IdRes int viewId, @StringRes int resId) {
        TextView textView = retrieveView(viewId);
        textView.setHint(resId);
        return this;
    }

    public BaseRvHolder setHint(@IdRes int viewId, String hint) {
        TextView textView = retrieveView(viewId);
        textView.setHint(hint);
        return this;
    }

    public BaseRvHolder addTextChangedListener(@IdRes int viewId, TextWatcher watcher) {
        TextView textView = retrieveView(viewId);
        textView.addTextChangedListener(watcher);
        return this;
    }

    public BaseRvHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView imageView = retrieveView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public BaseRvHolder setImageResource(@IdRes int viewId, @DrawableRes int resId) {
        ImageView imageView = retrieveView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    /**
     * 保留方法,自己根据项目进行修改
     * 也可以添加设置圆角的图片url等等，根据需求添加  ScaleType -2 圆形
     */
    public BaseRvHolder setImageUrl(Context context, String url, @IdRes int viewId, int ScaleType, int defaultImg) {
        ImageView imageView = retrieveView(viewId);
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.color.C6);
            return this;
        }
        //请根据自己项目使用的图片加载框架来加载
        if (ScaleType == -2) {
            GlideUtils.loadOvalImage(context, url, 0, imageView);
        } else {
            GlideUtils.loadImage(context, url, imageView, ScaleType, defaultImg);
        }
        return this;
    }

    public BaseRvHolder setProgress(@IdRes int viewId, int progress) {
        ProgressBar progressBar = retrieveView(viewId);
        progressBar.setProgress(progress);
        return this;
    }

    public BaseRvHolder setProgressMax(@IdRes int viewId, int max) {
        ProgressBar progressBar = retrieveView(viewId);
        progressBar.setMax(max);
        return this;
    }

    /**
     * 通过viewId从缓存中获取View
     * <p>
     * 对View进行缓存处理
     */
    public <T extends View> T retrieveView(@IdRes int viewId) {
        View retrieveView = mViewArray.get(viewId);
        if (retrieveView == null) {
            retrieveView = getItemView().findViewById(viewId);
            mViewArray.put(viewId, retrieveView);
        }
        return (T) retrieveView;
    }

    /**
     * 获取非空的text，null或者empty时候可以设置默认值
     * 对content, defaultContent都进行判空操作
     */
    private String obtainNoNullText(String content, String defaultContent) {
        if (TextUtils.isEmpty(content)) {
            return TextUtils.isEmpty(defaultContent) ? "" : defaultContent;
        } else {
            return content;
        }
    }
}