package com.v.tinker_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.v.tinker_module.utils.FileUtils;
import com.v.tinker_module.utils.FixDexUtils;

import java.io.File;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void calculate(View view) {
        Calculator calculator = new Calculator();
        calculator.calculate(this);
    }

    public void fix(View view) {
        fixBug();
    }

    private void fixBug() {
        //1 从服务器下载dex文件 比如v1.1修复包文件（classes2.dex）
        File sourceFile = new File(Environment.getExternalStorageDirectory(), "classes2.dex");
        // 目标路径：私有目录
        //getDir("odex", Context.MODE_PRIVATE) data/user/0/包名/app_odex
        File targetFile = new File(getDir("odex",
                Context.MODE_PRIVATE).getAbsolutePath() + File.separator + "classes2.dex");
        if (targetFile.exists()) {
            targetFile.delete();
        }
        try {
            // 复制dex到私有目录
            FileUtils.copyFile(sourceFile, targetFile);
            Toast.makeText(this, "复制到私有目录完成", Toast.LENGTH_SHORT).show();
            FixDexUtils.loadFixedDex(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}