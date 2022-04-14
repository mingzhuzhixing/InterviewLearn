package com.v.module_android_basic.fragment_interface;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.v.module_android_basic.R;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-24 18:08
 */

public class BlankFragment1 extends BaseFragment {
    private IReduceListener listener;

    public static final String INTERFACE = BlankFragment1.class.getName() + "NPNR";

    private View mView;

    //提供Activity调用的方法
    public void setListener(IReduceListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_blank_fragment_1, container, false);
        mView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        listener.update(123);//向Activity传递值

        return mView;
    }
}
