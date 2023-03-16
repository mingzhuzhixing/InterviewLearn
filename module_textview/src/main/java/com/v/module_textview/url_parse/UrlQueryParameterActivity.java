package com.v.module_textview.url_parse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_textview.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 获取URI后面的参数
 */
@SuppressLint("SetTextI18n")
public class UrlQueryParameterActivity extends BaseTitleBarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_url_query_parameter;
    }

    @Override
    protected String setTitle() {
        return "获取URI中的参数";
    }

    @Override
    protected void initData() {
        String url = "http://zaiadev.laidan.com/p/newbee/newbornZone?id=000&back=1234&search=90";

        TextView tvUrl = findViewById(R.id.tv_url);
        tvUrl.setText("url:" + url);

//        Uri uri = Uri.parse(url);
//        String content = uri.getQueryParameter("back");
//        TextView textView = findViewById(R.id.tv_content);
//        textView.setText("获取参数back值：" + content);
//
//        TextView textKeyValue = findViewById(R.id.tv_content_key_value);
//        Map<String, String> map = getParams(url);
//        StringBuilder builder = new StringBuilder();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            builder.append("key:").append(entry.getKey()).append("---value:").append(entry.getValue()).append("\n");
//        }
//        textKeyValue.setText(builder.toString());




        //String scheme = "youshuapp://jump_mini_program?work_path=pages/article/article.html?articleUrl=https%3A%2F%2Fzaiadev.laidan.com%2Fmp%2Fentry%3Furl_code%3Djoin_class%26stage_id%3D5BRV7AMXZP\\u0026work_username=gh_c0cc2ecae660";

        String scheme = "youshuapp://jump_mini_program?work_path=pages/article/article.html?articleUrl=https%3A%2F%2Fzaiadev.laidan.com%2Fmp%2Fentry%3Furl_code%3Djoin_class%26stage_id%3D5BRV7AMXZP\u0026work_username=gh_c0cc2ecae660";
        Uri uri = Uri.parse(scheme);
        String work_path = getSchemeQueryParameter(uri, "work_path");
        String work_username = getSchemeQueryParameter(uri, "work_username");

        Log.i("zm1234", "work_path: "+work_path);

        if(work_path.contains("articleUrl=")){
            Uri uriUrl = Uri.parse(work_path);
            String articleUrl = getSchemeQueryParameter(uriUrl, "articleUrl");
            Log.i("zm1234", "articleUrl: "+articleUrl);
        }

        Log.i("zm1234", "work_username: "+work_username);
        //System.out.println("work_username:"+work_username);
    }

    public String getSchemeQueryParameter(Uri uri, String key) {
        String content = uri.getQueryParameter(key);
        String str = TextUtils.isEmpty(content) ? "" : content;
        return str;
    }

    @Override
    protected void processLogical() {

    }

    public static Map<String, String> getParams(String url) {
        if (TextUtils.isEmpty(url))
            return Collections.emptyMap();
        try {
            Uri uri = Uri.parse(url);
            Map<String, String> params = new HashMap<>();
            Set<String> names = uri.getQueryParameterNames();
            for (String name : names) {
                params.put(name, uri.getQueryParameter(name));
            }
            return params;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }
}