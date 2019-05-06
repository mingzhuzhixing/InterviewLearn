package com.v.taopiaopiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(that,"加载了。。。",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(that,SecondActivity.class));
            }
        });
    }
}
