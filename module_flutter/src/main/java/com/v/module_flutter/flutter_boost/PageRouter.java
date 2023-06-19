package com.v.module_flutter.flutter_boost;

import android.content.Context;
import android.content.Intent;

import java.util.Map;

public class PageRouter {
    public static final String FLUTTER_SPLASH_PAGE = "sample://splash";

    public static boolean openPageByUrl(Context context, String url, Map params) {
        return openPageByUrl(context, url, params, 0);
    }

    public static boolean openPageByUrl(Context context, String url, Map params, int requestCode) {
        try {
//            Intent intent = new Intent(context, FlutterPageActivity.class);
//            intent.putExtra("url", url);
//            context.startActivity(intent);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }
}
