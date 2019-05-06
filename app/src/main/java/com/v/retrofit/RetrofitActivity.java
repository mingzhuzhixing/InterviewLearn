package com.v.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.v.interviewlearn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        //步骤3：在MainActivity创建接口类实例
        AccessApi netService = RetrofitConfig.getBaseRetrofit().create(AccessApi.class);

        //步骤4：对发送请求的url进行封装，即生成最终的网络请求对象  -->
        Call<JavaBean> call = netService.getCall();
        call.enqueue(new Callback<JavaBean>() {
            @Override
            public void onResponse(Call<JavaBean> call, Response<JavaBean> response) {

            }

            @Override
            public void onFailure(Call<JavaBean> call, Throwable t) {

            }
        });

    }
}
