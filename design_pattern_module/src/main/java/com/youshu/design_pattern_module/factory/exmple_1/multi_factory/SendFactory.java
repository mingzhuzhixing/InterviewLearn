package com.youshu.design_pattern_module.factory.exmple_1.multi_factory;

import com.youshu.design_pattern_module.factory.exmple_1.MailSender;
import com.youshu.design_pattern_module.factory.exmple_1.Sender;
import com.youshu.design_pattern_module.factory.exmple_1.SmsSender;

/**
 * 多个工厂方法模式：是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象，而多个工厂方法模式是提供多个工厂方法，分别创建对象
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 10:22
 */

public class SendFactory {

    /**
     * 多个工厂方法模式
     */
    public Sender produceMail(){
        return new MailSender();
    }

    public Sender produceSms(){
        return new SmsSender();
    }
}
