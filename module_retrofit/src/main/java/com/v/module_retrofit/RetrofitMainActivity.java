package com.v.module_retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_retrofit.api.AccessApi;
import com.v.module_retrofit.bean.JavaBean;
import com.v.module_retrofit.http.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitMainActivity extends AppCompatActivity {

    private final String TAG = "RetrofitMainActivity";
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_retrofit);
        tvResult = findViewById(R.id.tv_result);
        findViewById(R.id.btn_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRequestApi();
            }
        });
    }

    /**
     * 设置请求的api
     */
    private void setRequestApi() {
        //步骤3：在MainActivity创建接口类实例
        AccessApi netService = RetrofitConfig.getBaseRetrofit().create(AccessApi.class);

        //步骤4：对发送请求的url进行封装，即生成最终的网络请求对象  -->
        Call<JavaBean> call = netService.getCall();
        call.enqueue(new Callback<JavaBean>() {
            @Override
            public void onResponse(Call<JavaBean> call, Response<JavaBean> response) {
                Log.i(TAG, "onResponse:" + response.body().toString());
                tvResult.setText("请求结果：" + response.body().toString());
            }

            @Override
            public void onFailure(Call<JavaBean> call, Throwable t) {
                Log.i(TAG, "onFailure:" + t.getMessage());
                tvResult.setText("请求失败：" + t.getMessage());
            }
        });
    }
}
