package com.v.modularization;

import com.v.interviewlearn.MainActivity;
import com.v.route.ARoute;
import com.v.route.IRoute;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-10 18:18
 */
public class ActivityUtil implements IRoute {

    @Override
    public void putActivity() {
        ARoute.getInstance().putActivity("main/main", MainActivity.class);
    }
}
