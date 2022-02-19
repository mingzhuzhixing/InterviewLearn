package com.v.mvp_module.model;

import com.v.mvp_module.bean.Girl;

import java.util.List;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-05 15:20
 */
public interface IGirlModel {

    void loadGirl(GirlOnLoadListener listener);

    interface GirlOnLoadListener{
        void  onComplete(List<Girl> girls);
    }
}
