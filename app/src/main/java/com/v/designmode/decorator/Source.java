package com.v.designmode.decorator;

/**
 * 被装饰类
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 11:43
 */

public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
