package com.youshu.design_pattern_module.observer;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 15:33
 */

public interface Subject {

    /**
     * 增加观察者
     */
    public void add(Observer observer);

    /**
     * 删除观察者
     */
    public void del(Observer observer);

    /**
     * 通知所有的观察者
     */
    public void notifyObservers();

    /**
     * 自身的操作
     */
    public void operation();
}
