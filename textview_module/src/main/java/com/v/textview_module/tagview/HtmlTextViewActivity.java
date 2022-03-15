package com.v.textview_module.tagview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.v.textview_module.R;

public class HtmlTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_text_view);

        TextView text1 = (TextView) findViewById(R.id.text1);
        TextView text2 = (TextView) findViewById(R.id.text2);
        TextView text3 = (TextView) findViewById(R.id.text3);
        TextView text4 = (TextView) findViewById(R.id.text4);

        String s = "zhang phil @ csdn Android TextView图文混编";

        CharSequence cs1 = Html.fromHtml(stringMixWithImage1(s), imgageGetter, null);
        text1.setText(cs1);

        CharSequence cs2 = Html.fromHtml(stringMixWithImage2(s), imgageGetter, null);
        text2.setText(cs2);

        CharSequence cs3 = Html.fromHtml(stringMixWithImage3(s), imgageGetter, null);
        text3.setText(cs3);

        CharSequence cs4 = Html.fromHtml(stringMixWithImage4(s), imgageGetter, null);
        text4.setText(cs4);
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