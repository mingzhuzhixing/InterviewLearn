package com.v.network_architecture;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * volley 实现类
 */
public class VolleyProcessor implements IHttpProcessor{
    private static RequestQueue mQueue=null;

    public VolleyProcessor(Context context){
        mQueue=Volley.newRequestQueue(context);
    }

    /**
     * post请求
     * @param url
     * @param params
     * @param callback
     */
    @Override
    public void post(String url, Map<String, Object> params, final ICallback callback) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callback.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callback.onFailure(volleyError.toString());
            }
        });
        mQueue.add(stringRequest);
    }


    /**
     * get请求
     * @param url
     * @param params
     * @param callback
     */
    @Override
    public void get(String url, Map<String, Object> params, final ICallback callback) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callback.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callback.onFailure(volleyError.toString());
            }
        });
        mQueue.add(stringRequest);
    }
}
