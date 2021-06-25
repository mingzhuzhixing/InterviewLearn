package com.youshu.design_pattern_module.factory.exmple_1.static_factory;

import com.youshu.design_pattern_module.factory.exmple_1.Sender;

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
         * 静态工厂方法模式
         */
        Sender sender = SendFactory.produceMail();
        sender.send();
    }
}
