package com.v.designmode.proxy;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 14:32
 */

public class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
