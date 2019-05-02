package com.v.designmode.observer;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 15:32
 */

public class Observer1 implements Observer {

    @Override
    public void update() {
        System.out.println("observer1 has received!");
    }
}
