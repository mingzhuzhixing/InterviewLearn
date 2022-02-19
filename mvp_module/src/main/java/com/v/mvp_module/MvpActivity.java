package com.v.mvp_module;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.v.mvp_module.adapter.GirlAdapter;
import com.v.mvp_module.bean.Girl;
import com.v.mvp_module.view.GirlPresenterV2;
import com.v.mvp_module.view.IGirlView;

import java.util.List;

public class MvpActivity extends AppCompatActivity implements IGirlView {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        listview = findViewById(R.id.listview);

//        new GirlPresenterV1(this).fetch();
        new GirlPresenterV2(this).fetch();
    }

    @Override
    public void showLoading() {
        Toast.makeText(this,"正在拼命加载中，，，",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGirls(List<Girl> girls) {
        listview.setAdapter(new GirlAdapter(this,girls));
    }
}

