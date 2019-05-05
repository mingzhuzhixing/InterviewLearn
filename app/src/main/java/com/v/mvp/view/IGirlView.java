package com.v.mvp.view;

import com.v.mvp.bean.Girl;

import java.util.List;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-05 15:24
 */
public interface IGirlView {

    /**
     * 加载进度条
     */
    void showLoading();

    void showGirls(List<Girl> girls);
}
