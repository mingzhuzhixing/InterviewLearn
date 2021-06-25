package com.youshu.design_pattern_module.factory.exmple_1.abstract_factory;


import com.youshu.design_pattern_module.factory.exmple_1.Sender;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 10:46
 */

public class FactoryTest {

    public static void main(String[] args) {
        SendMailFactory factory=new SendMailFactory();
        Sender sender=factory.produce();
        sender.send();
    }
}
