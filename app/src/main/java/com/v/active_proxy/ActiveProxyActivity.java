package com.v.active_proxy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.v.interviewlearn.R;

import java.lang.reflect.Proxy;

public class ActiveProxyActivity extends AppCompatActivity implements ILogin {
    private ILogin proxyLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_proxy);

        /**
         * 第一个参数：类加载器
         * 第二个参数：代理对象的目标类
         * 第三个参数：回调处理类
         */
        proxyLogin = (ILogin) Proxy.newProxyInstance(this.getClassLoader(),
                new Class[]{ILogin.class}, new MyHandler(this, this));
    }

    public void me(View view) {
        proxyLogin.toLogin();
    }

    public void play(View view) {
    }

    public void look(View view) {
    }

    @Override
    public void toLogin() {
        Intent intent=new Intent(this,ActiveProxyMemberActivity.class);
        startActivity(intent);
    }
}
