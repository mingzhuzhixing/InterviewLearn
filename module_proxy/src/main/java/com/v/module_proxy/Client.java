package com.v.module_proxy;

import com.v.module_proxy.dyna_proxy.DynamicProxy;
import com.v.module_proxy.static_proxy.Lawyer;
import com.v.module_proxy.static_proxy.XiaoMin;

import java.lang.reflect.Proxy;

/**
 * 客户测试类
 */
public class Client {
    public static void main(String[] args) {

        //staticProxy();

        dynamicProxy();
    }

    /**
     * 静态代理
     */
    private static void staticProxy() {
        //构造一个小民出来
        ILawsuit xiaoMin = new XiaoMin();
        //构造一个代理律师并将小民作为构造参数传递进去
        Lawyer lawyer = new Lawyer(xiaoMin);
        //律师提交诉讼
        lawyer.submit();
        //律师进行举证
        lawyer.burden();
    }

    /**
     * 动态代理
     */
    private static void dynamicProxy() {
        //构造一个小民出来
        ILawsuit xiaoMin = new XiaoMin();
        //获取被代理类小民的ClassLoader
        ClassLoader classLoader = xiaoMin.getClass().getClassLoader();

        //动态构造一个代理者律师
        ILawsuit lawyer = (ILawsuit) Proxy.newProxyInstance(classLoader, new Class[]{ILawsuit.class}, new DynamicProxy(xiaoMin));
        //律师提交诉讼
        lawyer.submit();
        //律师进行举证
        lawyer.burden();
    }
}
