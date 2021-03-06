package com.v.network_architecture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.v.interviewlearn.R;
import com.v.network_architecture.entity.Weather;

import java.util.HashMap;
import java.util.Map;

public class TestNetworkActivity extends AppCompatActivity {
    private String url="http://c.3g.163.com/photo/api/set/0001%2F2250173.json";

    private Map<String, Object> params = new HashMap<>();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_network);
        textView=findViewById(R.id.text);
    }

    /**
     * 请求网络
     * @param view
     */
    public void requestNetwork(View view) {

        HttpHelper.obtain().post(url, params, new HttpCallback<Weather>() {
            @Override
            public void onFailure(String e) {
                Toast.makeText(TestNetworkActivity.this,"请求失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(Weather weather) {
                textView.setText(weather.getCreator()+"\n"+weather.getDesc());
            }
        });
    }

    private void VolleyNetwork(){}

}
