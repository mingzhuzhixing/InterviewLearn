package comv.module_network;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import comv.module_network.entity.Weather;

@SuppressLint("SetTextI18n")
public class NetworkMainActivity extends AppCompatActivity {
    private String url = "http://c.3g.163.com/photo/api/set/0001%2F2250173.json";

    private Map<String, Object> params = new HashMap<>();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_network);
        textView = findViewById(R.id.text);
    }

    /**
     * 请求网络
     */
    public void requestNetwork(View view) {
        HttpHelper.obtain().post(url, params, new HttpCallback<Weather>() {
            @Override
            public void onFailure(String e) {
                Toast.makeText(NetworkMainActivity.this, "请求失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(Weather weather) {
                textView.setText(weather.getCreator() + "\n" + weather.getDesc());
            }
        });
    }

    private void VolleyNetwork() {
    }
}
