package com.youshu.design_pattern_module.observer;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 15:32
 */

public class Observer2 implements Observer {

    @Override
    public void update() {
        System.out.println("observer2 has received!");
    }
}
