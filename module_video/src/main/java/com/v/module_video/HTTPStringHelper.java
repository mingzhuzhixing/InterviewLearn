package com.v.module_video;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @author wangbq
 * @package com.youshu.network.interceptor
 * @time 2020/9/27
 */
public class HTTPStringHelper {

    public static String getKeySetString(Map<String, String> map) {
        StringBuffer buf = new StringBuffer(map.size() * 20);
        Collection<String> keyset = map.keySet();
        List<String> list = new ArrayList<>(keyset);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            String string = list.get(i);
            buf.append("/").append(string).append("/").append(map.get(string));
        }
        return buf.toString();
    }

    public static String getUserAgent(Context context) {
        String userAgent = "";
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                //api < 19
                userAgent = System.getProperty("http.agent");
            } else {
                //api >= 19
                userAgent = WebSettings.getDefaultUserAgent(context);
                if (TextUtils.isEmpty(userAgent)) {
                    userAgent = System.getProperty("http.agent");
                }
            }
        } catch (Exception e) {
            userAgent = System.getProperty("http.agent");
            //正常容错逻辑，不需要上报
            e.printStackTrace();
        }

        // 个别国产手机携带中文 , 需要进行转码过滤
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        try {
            sb.append("  "+"youshuapp/"+ context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
