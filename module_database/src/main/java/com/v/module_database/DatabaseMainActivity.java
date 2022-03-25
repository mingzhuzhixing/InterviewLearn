package com.v.module_database;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class DatabaseMainActivity extends AppCompatActivity {

    //    BaseDao baseDao;
    BaseDao<Person> baseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_main);

//        String sqliteDatabasePath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/dongnao.db";
//        SQLiteDatabase sqLiteDatabase=SQLiteDatabase.openOrCreateDatabase(sqliteDatabasePath,null);
//
//        baseDao=new BaseDao();
//        baseDao.init(Person.class,sqLiteDatabase);

        //请求权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 如果没有被授予, 直接去请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            baseDao = BaseDaoFactory.getInstance().getBaseDao(Person.class);
        }
    }


    public void insertData(View view) {
        Person person = new Person("王五", 123456L, 25);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        person.setPicture(bitmap2Bytes(bitmap));
        baseDao.insert(person);
    }

    private byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    @SuppressLint("SetTextI18n")
    public void searchData(View view) {
        ImageView imageView = findViewById(R.id.image);
        TextView tvInfo = findViewById(R.id.tv_info);
        Person where = new Person();
        where.setName("王五");
        where.setAge(25);

        new Thread(new Runnable() {

            @Override
            public void run() {
                List<Person> list = baseDao.queue(where);
                if (list.isEmpty()) {
                    return;
                }
                Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(0).getPicture(), 0, list.get(0).getPicture().length);
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        tvInfo.setText(list.get(0).name + ": " + list.get(0).age);
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }

    //申请权限结果返回处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            boolean isAllGranted = true;
            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            //判断所有的权限都授予了
            if (isAllGranted) {

            } else {
                Toast.makeText(this, "某些权限未开启,请手动开启", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }
}
