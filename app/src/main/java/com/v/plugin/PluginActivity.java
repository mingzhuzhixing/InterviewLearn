package com.v.plugin;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
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
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        File file=new File(Environment.getExternalStorageDirectory(),"plugin.apk");
        PluginManager.getInstance().loadApk(file.getAbsolutePath());
    }

    public void entryActivity(View view){
        Toast.makeText(PluginActivity.this,"fndasfjdf",Toast.LENGTH_SHORT).show();

//        Intent intent=new Intent(PluginActivity.this, ProxyActivity.class);
//        intent.putExtra("className",PluginManager.getInstance().getPackageInfo().activities[0].name);
//        startActivity(intent);
    }
}
