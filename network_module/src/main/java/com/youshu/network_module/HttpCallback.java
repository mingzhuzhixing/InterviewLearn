package com.youshu.network_module;

import com.google.gson.Gson;
import com.youshu.network_module.listener.ICallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallback<Result> implements ICallback {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> cla = analysisClassInfo(this);
        Result objResult = (Result) gson.fromJson(result, cla);
        onSuccess(objResult);
    }

    public abstract void onSuccess(Result result);

    public static Class<?> analysisClassInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        System.out.println(params[0]);
        return (Class<?>) params[0];
    }
}
