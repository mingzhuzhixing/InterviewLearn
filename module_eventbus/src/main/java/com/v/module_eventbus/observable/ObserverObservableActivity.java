package com.v.module_eventbus.observable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.v.module_eventbus.R;

public class ObserverObservableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer_observable);
    }

    public void onClickObservable(View view) {
        ObservableTeacher teacher = new ObservableTeacher();
        ObserverStudent student = new ObserverStudent("小明");

        teacher.addObserver(student);

        teacher.publishMessage("下課了");
    }
}