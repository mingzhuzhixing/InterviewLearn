package com.v.module_eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_eventbus.bean.Friend;
import com.v.module_eventbus.eventbus.DNEventbus;
import com.v.module_eventbus.eventbus.DNSubscribe;
import com.v.module_eventbus.eventbus.DNThreadMode;
import com.v.module_eventbus.observable.ObserverObservableActivity;


public class EventBusMainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_event_bus);

//        EventBus.getDefault().register(this);
        DNEventbus.getDefault().register(this);

        textView = (TextView) findViewById(R.id.tv);
    }

    public void change(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void onObservableClick(View view) {
        startActivity(new Intent(this, ObserverObservableActivity.class));
    }


    @DNSubscribe(threadMode = DNThreadMode.MAIN)
    public void receive(Friend friend){
//        textView.setText(friend.toString() + "==" + Thread.currentThread().getName());
        Toast.makeText(this, friend.toString(), Toast.LENGTH_SHORT).show();
    }

//    @Subscribe
//    public void receive(String s){
//        textView.setText(s);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
        DNEventbus.getDefault().unregister(this);
    }
}
