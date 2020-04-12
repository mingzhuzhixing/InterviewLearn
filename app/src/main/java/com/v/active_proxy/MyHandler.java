package com.v.active_proxy;

import android.content.Context;
import android.content.Intent;

import com.v.utils.SharePreferenceUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class
MyHandler implements InvocationHandler {
    private Object traget;
    private Context context;

    public MyHandler(Object traget, Context context) {
        this.traget = traget;
        this.context = context;
    }

    /**
     * 这个invoke就是拦截Object对象的所有方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        //
        if (SharePreferenceUtil.getBooleanSp(SharePreferenceUtil.IS_LOGIN, context)) {
            result = method.invoke(traget, args);
        } else {
            Intent intent = new Intent(context, ActiveProxyLoginActivity.class);
            context.startActivity(intent);
        }


        return null;
    }
}
