package com.v.mvp_module.model;

import com.v.mvp_module.R;
import com.v.mvp_module.bean.Girl;

import java.util.ArrayList;
import java.util.List;

/**
 * 网络加载数据
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-05 15:27
 */
public class GirlModelImplV2 implements IGirlModel {

    @Override
    public void loadGirl(GirlOnLoadListener listener) {
        //模拟从网络加载
        List<Girl> data=new ArrayList<>();
        for (int i=0;i<10;i++){
            data.add(new Girl(R.drawable.ic_launcher_background,"姓名---："+i,"描述----："+i));
        }

        //回调
        listener.onComplete(data);
    }
}
