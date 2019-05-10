package com.v.route;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

/**
 * 模块之间跳转的类
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-10 17:26
 */
public class ARoute {

    /**
     * 装载所有的activity的class对象集合
     */
    private HashMap<String,Class<? extends Activity>> activityList;

    private static ARoute mARoute=new ARoute();

    //上下文
    private Context mContext;

    private ARoute(){
        activityList=new HashMap<>();

    }

    public static ARoute getInstance(){
        return mARoute;
    }

    public void init(Application application){
        mContext=application.getApplicationContext();
    }

    public HashMap<String, Class<? extends Activity>> getActivityList() {
        return activityList;
    }

    /**
     * 存入activity方法
     *
     * @param path
     * @param aClass
     */
    public void putActivity(String path, Class<? extends Activity> aClass) {
        if (path ==null || aClass==null){
            return;
        }
        activityList.put(path,aClass);
    }

    /**
     * 跳转的activity
     */
    public void jumpActivity(String path, Bundle bundle){
        Class<? extends Activity> aClass=activityList.get(path);
        if (aClass==null){
            return;
        }

        Intent intent=new Intent().setClass(mContext,aClass);
        if(bundle!=null){
            intent.putExtra("bundle",bundle);
        }
        mContext.startActivity(intent);
    }
}
