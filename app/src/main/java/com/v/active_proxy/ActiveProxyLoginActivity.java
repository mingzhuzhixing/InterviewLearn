package com.v.active_proxy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.v.interviewlearn.R;
import com.v.utils.SharePreferenceUtil;

public class ActiveProxyLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_proxy_login);
    }

    public void login(View view) {
        SharePreferenceUtil.setBooleanSp(SharePreferenceUtil.IS_LOGIN, true, this);
        finish();
    }
}
