package com.v.textview_module.tagview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.v.textview_module.R;

/**
 * SpannableString中的setSpan使用的flag 一般有以下四种：
 * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE --- 不包含两端start和end所在的端点
 * Spanned.SPAN_EXCLUSIVE_INCLUSIVE --- 不包含端start，但包含end所在的端点
 * Spanned.SPAN_INCLUSIVE_EXCLUSIVE --- 包含两端start，但不包含end所在的端点
 * Spanned.SPAN_INCLUSIVE_INCLUSIVE--- 包含两端start和end所在的端点
 * 其实这4个flag在用于不可编辑的控件中时，效果是一样的，可以随便用。
 * <p>
 * 但是在EditText等可以编辑的控件中使用时，就会有区别。
 * 对一个SpannableString的编辑有两种情况：在它的开始或结尾处插入新的内容。
 * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE：新插入的内容与原来的SpannableString独立存在，不混合在一起。
 * Spanned.SPAN_EXCLUSIVE_INCLUSIVE：在结尾处插入新内容时，会与原来的SpannableString混合在一起，组成一个新的SpannableString
 * Spanned.SPAN_INCLUSIVE_EXCLUSIVE：在开始处插入新内容时，会与原来的SpannableString混合在一起，组成一个新的SpannableString
 * Spanned.SPAN_INCLUSIVE_INCLUSIVE：在开始或结尾处处插入新内容时，会与原来的SpannableString混合在一起，组成一个新的SpannableString
 */
public class SpannableStringActivity extends AppCompatActivity {

    final String content = "图孩子的进步一定是建立在与自己比较的基础之上，要客观的看待孩子大大小小进步。今天比昨天进步一点点，能力强的孩子们进,。今天比昨天进步一点点，能力强的孩子们进";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string);

        TextView text1 = (TextView) findViewById(R.id.text1);
        TextView text5 = (TextView) findViewById(R.id.text5);

        testHighLight(text1);

        imageAndText(text5);
    }

    /**
     * 文字显示高亮
     */
    public void testHighLight(TextView textView) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(content);//用于可变字符串
        ForegroundColorSpan span1 = new ForegroundColorSpan(Color.RED);
        spannable.setSpan(span1, 3, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //修改字体颜色
        ForegroundColorSpan span2 = new ForegroundColorSpan(Color.BLUE);
        spannable.setSpan(span2, 9, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //加下滑线
        CharacterStyle span = new UnderlineSpan();
        spannable.setSpan(span, 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //字体斜体
        CharacterStyle span_1 = new StyleSpan(Typeface.ITALIC);
        spannable.setSpan(span_1, 19, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //字体大小 true表示单位是dp
        CharacterStyle span_2 = new AbsoluteSizeSpan(40, true);
        spannable.setSpan(span_2, 24, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //字体加粗
        CharacterStyle span_3 = new StyleSpan(Typeface.BOLD);
        spannable.setSpan(span_3, 30, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //字体点击
        //这个一定要记得设置，不然点击不生效
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        //设置点击后的颜色
        textView.setHighlightColor(getResources().getColor(android.R.color.transparent));
        spannable.setSpan(new TextClick(), 39, 47, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new UnderlineSpan(), 39, 47, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f0831e")), 39, 47, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }

    private class TextClick extends ClickableSpan {
        @Override
        public void onClick(@NonNull View widget) {
            //在此处理点击事件
            Log.e("------->", "点击了");
            Toast.makeText(SpannableStringActivity.this, "点击了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(true);
        }
    }

    /**
     * 图文混合
     */
    private void imageAndText(TextView textView) {
        final SpannableString ss = new SpannableString(" " + content + " ");
        //得到drawable对象，即所要插入的图片
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ee_1);
        ImageSpan span = null;
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            //用这个drawable对象代替字符串content
            span = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        }

        Drawable drawable2 = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ee_2);
        ImageSpan span2 = null;
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
            //用这个drawable对象代替字符串content
            span2 = new ImageSpan(drawable2, ImageSpan.ALIGN_BOTTOM);
        }
        if (span != null) {
            //包括0但是不包括content.length()即：1。[0,1)。值得注意的是当我们复制这个图片的时候，实际是复制了"图"这个字符串。
            ss.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (span2 != null) {
            ss.setSpan(span2, ss.length() - 1, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(ss);
    }
}