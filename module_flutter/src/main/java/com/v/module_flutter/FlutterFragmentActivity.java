package com.v.module_flutter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import io.flutter.embedding.android.FlutterFragment;

/**
 * 使用新的 FlutterEngine 向 Activity 中添加 FlutterFragment
 */
public class FlutterFragmentActivity extends FragmentActivity {
    private static final String TAG_FLUTTER_FRAGMENT = "flutter_fragment";

    private FlutterFragment flutterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();

        flutterFragment = (FlutterFragment) fragmentManager.findFragmentByTag(TAG_FLUTTER_FRAGMENT);
        if (flutterFragment == null) {
            // flutterFragment = FlutterFragment.createDefault();

            // 默认情况下，FlutterFragment 会创建它自己的 FlutterEngine 实例，同时也需要不少的启动时间。这就意味着您的用户会看到短暂的白屏。
            // 通过使用已存在的、预热的 FlutterEngine 就可以大幅度减少启动的耗时。
            // 要在 FlutterFragment 中使用预热 FlutterEngine，可以使用工厂方法 withCachedEngine() 实例化 FlutterFragment。
            flutterFragment = FlutterFragment.withCachedEngine("my_engine_id").build();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, flutterFragment, TAG_FLUTTER_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void onPostResume() {
        super.onPostResume();
        flutterFragment.onPostResume();
    }

    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        flutterFragment.onNewIntent(intent);
    }

    @Override
    public void onBackPressed() {
        flutterFragment.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        flutterFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onUserLeaveHint() {
        flutterFragment.onUserLeaveHint();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        flutterFragment.onTrimMemory(level);
    }
}