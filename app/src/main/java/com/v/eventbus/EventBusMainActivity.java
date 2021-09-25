package com.v.eventbus;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.v.interviewlearn.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusMainActivity extends AppCompatActivity {
    TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_main);
        tvContent = findViewById(R.id.tv_content);

        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void notify(String s) {
        Log.i("zmmm", "s--notify-->" + s);
        tvContent.setText(s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void notifyy(String s) {
        Log.i("zmmm", "s--notifyy===->" + s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void entry(View view) {
        startActivity(new Intent(this, EventBusSecondActivity.class));
    }
}
