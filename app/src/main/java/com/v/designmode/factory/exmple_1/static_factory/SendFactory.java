package com.v.designmode.factory.exmple_1.static_factory;

import com.v.designmode.factory.exmple_1.MailSender;
import com.v.designmode.factory.exmple_1.Sender;
import com.v.designmode.factory.exmple_1.SmsSender;

/**
 * 静态工厂方法模式，将上面的多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可。
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 10:22
 */

public class SendFactory {

    /**
     * 静态工厂方法模式
     */
    public static  Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }
}
