package com.v.module_flutter.flutter_native;

import android.os.Bundle;
import android.util.Log;

import io.flutter.embedding.android.FlutterActivity;

public class FlutterChildActivity extends FlutterActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_flutter_child);
//    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("FlutterChildActivity", "onResume: ");
    }
}