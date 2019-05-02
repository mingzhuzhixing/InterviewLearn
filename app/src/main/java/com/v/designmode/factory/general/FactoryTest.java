package com.v.designmode.factory.general;

import com.v.designmode.factory.Sender;

/**
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 10:24
 */

public class FactoryTest {

    public static void main(String[] args) {

        /**
         * 普通工厂模式
         */
        SendFactory factory = new SendFactory();
        Sender sender = factory.produce("sms");
        sender.send();

    }
}
