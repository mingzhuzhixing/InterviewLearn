package com.v.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.v.interviewlearn.R;

import java.io.File;

public class PluginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        PluginManager.getInstance().setContext(this);
    }

    public void loadPlugin(View view){
        File file=new File(Environment.getExternalStorageDirectory(),"plugin.apk");
        PluginManager.getInstance().loadApk(file.getAbsolutePath());
    }

    public void entryActivity(View view){
        Toast.makeText(PluginActivity.this,"fndasfjdf",Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(PluginActivity.this, ProxyActivity.class);
        intent.putExtra("className",PluginManager.getInstance().getPackageInfo().activities[0].name);
        startActivity(intent);
    }
}
