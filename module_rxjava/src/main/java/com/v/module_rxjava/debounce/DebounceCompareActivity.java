package com.v.module_rxjava.debounce;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.v.module_rxjava.R;
import com.v.module_rxjava.listener.OnMultiClickListener;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * 防抖对比
 */
public class DebounceCompareActivity extends AppCompatActivity {

    private static final String TAG = "DebounceCompareActivity";

    private Button btn_old_debounce, btn_rxjava_debounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debounce_compare);
        btn_old_debounce = findViewById(R.id.btn_old_debounce);
        btn_rxjava_debounce = findViewById(R.id.btn_rxjava_debounce);

        initOldDebounce();

        initRxJavaDebounce();
    }

    /**
     * 传统防抖
     */
    private void initOldDebounce() {
        btn_old_debounce.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                Log.i(TAG, "传统防抖 执行了 时间差:" + (System.currentTimeMillis() - time));
                time = System.currentTimeMillis();
            }
        });
    }

    long time = System.currentTimeMillis();

    /**
     * RxJava防抖
     */
    @SuppressLint("CheckResult")
    private void initRxJavaDebounce() {
        RxView.clicks(btn_rxjava_debounce)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.i(TAG, "RxJava防抖 执行了 时间差:" + (System.currentTimeMillis() - time));
                        time = System.currentTimeMillis();
                    }
                });
    }
}