package com.v.module_textview.tagview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.v.module_textview.R;

public class HtmlTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_text_view);

        TextView text1 = (TextView) findViewById(R.id.text1);
        TextView text2 = (TextView) findViewById(R.id.text2);
        TextView text3 = (TextView) findViewById(R.id.text3);
        TextView text4 = (TextView) findViewById(R.id.text4);
        TextView text6 = (TextView) findViewById(R.id.text6);
        TextView text7 = (TextView) findViewById(R.id.text7);
        TextView text8 = (TextView) findViewById(R.id.text8);

        String s = "zhang phil @ csdn Android TextView图文混编";

        CharSequence cs1 = Html.fromHtml(stringMixWithImage1(s), imgageGetter, null);
        text1.setText(cs1);

        CharSequence cs2 = Html.fromHtml(stringMixWithImage2(s), imgageGetter, null);
        text2.setText(cs2);

        CharSequence cs3 = Html.fromHtml(stringMixWithImage3(s), imgageGetter, null);
        text3.setText(cs3);

        CharSequence cs4 = Html.fromHtml(stringMixWithImage4(s), imgageGetter, null);
        text4.setText(cs4);


        Spanned str6 = Html.fromHtml("我是默认字体 <b><font size=\"5\" color=\"blue\">设置字体加粗 蓝色 5号</font></b>");
        text6.setText(str6);

        //这种不起作用
        Spanned str7 = Html.fromHtml("我是默认字体 <font style=\"font-weight: bold;color:#ff0000;font-size:36px;\">设置字体加粗 蓝色 5号</font>");
        text7.setText(str7);

        Spanned str8 = Html.fromHtml("我是默认背景色  <strong><font color=#ff0000>" + "我是红色" + "</font></strong>  我是默认背景色");
        text8.setText(str8);
    }

    private String stringMixWithImage1(String string) {
        return string + "1 " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " ";
    }

    private String stringMixWithImage2(String string) {
        return "2 " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " " + string;
    }

    private String stringMixWithImage3(String string) {
        return string + "3 " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " ";
    }

    private String stringMixWithImage4(String string) {
        return "4 " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " " + "<img src='" + R.mipmap.ic_launcher + "'/>" + " " + string;
    }

    private Html.ImageGetter imgageGetter = new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String source) {
            int id = Integer.parseInt(source);
            Drawable d = ContextCompat.getDrawable(getApplicationContext(), id);
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            return d;
        }
    };
}