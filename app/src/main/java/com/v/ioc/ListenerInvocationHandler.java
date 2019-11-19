package com.v.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 * <p>
 * 我们要代理下面这段代码
 *
 * @OnClick({R.id.button1, R.id.button2})
 * @Override public void onClick(View v) {
 * }
 */
public class ListenerInvocationHandler implements InvocationHandler {
    private Object activity;
    private Method activityMethod;

    public ListenerInvocationHandler(Object activity, Method activityMethod) {
        this.activity = activity;
        this.activityMethod = activityMethod;
    }

    /**
     * 点击按钮后，执行这个方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在这里去调用被注解的click()
        return activityMethod.invoke(activity, args);
    }
}
