package com.v.interviewlearn.mvp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.v.interviewlearn.R;
import com.v.interviewlearn.mvp.MVPBaseActivity;
import com.v.interviewlearn.mvp.adapter.GirlAdapter;
import com.v.interviewlearn.mvp.bean.Girl;
import com.v.interviewlearn.mvp.view.GirlPresenterV3;
import com.v.interviewlearn.mvp.view.IGirlView;

import java.util.List;

public class Mvp3Activity extends MVPBaseActivity<IGirlView, GirlPresenterV3> implements IGirlView {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        listview = findViewById(R.id.listview);
        mPresenter.fetch();
    }

    @Override
    protected GirlPresenterV3 createPresenter() {
        return new GirlPresenterV3();
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
