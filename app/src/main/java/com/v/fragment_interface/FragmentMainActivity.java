package com.v.fragment_interface;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.v.fragment_interface.struct.FunctionNoParamNoResult;
import com.v.fragment_interface.struct.FunctionsManager;
import com.v.interviewlearn.R;

public class FragmentMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
    }


    public void setFunctionsForFragment(String tag){
        FragmentManager fm=getSupportFragmentManager();
        BaseFragment baseFragment= (BaseFragment) fm.findFragmentByTag(tag);
        FunctionsManager functionsManager=FunctionsManager.getInstance();
        baseFragment.setFunctionsManager(functionsManager.addFunction(new FunctionNoParamNoResult(BlankFragment1.INTERFACE) {
            @Override
            public void function() {
                Toast.makeText(FragmentMainActivity.this,"FunctionNoParamNoResult", Toast.LENGTH_LONG).show();
            }
        }));
    }


}
