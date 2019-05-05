package com.v.interviewlearn.mvp.model;

import com.v.interviewlearn.R;
import com.v.interviewlearn.mvp.bean.Girl;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-05 15:27
 */
public class GirlModelImplV1 implements IGirlModel {

    @Override
    public void loadGirl(GirlOnLoadListener listener) {
        List<Girl> data=new ArrayList<>();
        for (int i=0;i<10;i++){
            data.add(new Girl(R.drawable.ic_launcher_foreground,"姓名："+i,"描述："+i));
        }

        //回调
        listener.onComplete(data);
    }
}
