package com.v.database_module;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

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

        baseDao=BaseDaoFactory.getInstance().getBaseDao(Person.class);
    }


    public void insertData(View view) {
        Person person=new Person("王五",123456L,25);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        person.setPicture(bitmap2Bytes(bitmap));
        baseDao.insert(person);
    }

    private byte[] bitmap2Bytes(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        return baos.toByteArray();
    }



    public void searchData(View view) {
        ImageView imageView=findViewById(R.id.image);
        Person where=new Person();
        where.setName("李四");
        where.setAge(25);
        List<Person> list=baseDao.queue(where);
        Bitmap bitmap=BitmapFactory.decodeByteArray(list.get(0).getPicture(),0,list.get(0).getPicture().length);
        imageView.setImageBitmap(bitmap);
    }
}
