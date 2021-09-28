package com.v.fragment_interface;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.widget.Toast;

import com.v.fragment_interface.struct.FunctionNoParamNoResult;
import com.v.fragment_interface.struct.FunctionsManager;
import com.v.interviewlearn.R;

public class FragmentMainActivity extends AppCompatActivity implements IReduceListener {
    BlankFragment1 fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);

        fragment = new BlankFragment1();
        fragment.setListener(this);

    }

    @Override
    public void update(int count) {
        //实现具体接口内容
    }


    public void setFunctionsForFragment(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment baseFragment = (BaseFragment) fm.findFragmentByTag(tag);
        FunctionsManager functionsManager = FunctionsManager.getInstance();
        baseFragment.setFunctionsManager(functionsManager.addFunction(new FunctionNoParamNoResult(BlankFragment1.INTERFACE) {
            @Override
            public void function() {
                Toast.makeText(FragmentMainActivity.this, "FunctionNoParamNoResult", Toast.LENGTH_LONG).show();
            }
        }));
    }


}
