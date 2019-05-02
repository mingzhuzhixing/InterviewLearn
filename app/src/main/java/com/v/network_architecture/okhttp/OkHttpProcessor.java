package com.v.network_architecture.okhttp;

import android.os.Handler;

import com.v.network_architecture.ICallback;
import com.v.network_architecture.IHttpProcessor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpProcessor implements IHttpProcessor {

    private OkHttpClient okHttpClient;
    private Handler mHandler;

    public OkHttpProcessor(){
        okHttpClient=new OkHttpClient();
        mHandler=new Handler();
    }


    @Override
    public void post(String url, Map<String, Object> params, final ICallback callback) {
        RequestBody requestBody = appendBody(params);
        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("User-Agent","a")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call,final Response response) throws IOException {
                final String result=response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(response.isSuccessful()){
                            callback.onSuccess(result);
                        }else{
                            callback.onFailure(response.message());
                        }
                    }
                });
            }
        });
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callback) {

    }

    private RequestBody appendBody(Map<String, Object> params){

        FormBody.Builder body=new FormBody.Builder();
        if (params == null || params.isEmpty()){
            return body.build();
        }

        for (Map.Entry<String, Object> entry: params.entrySet()){
            body.add(entry.getKey(), entry.getValue().toString());
        }

        return body.build();
    }
}
