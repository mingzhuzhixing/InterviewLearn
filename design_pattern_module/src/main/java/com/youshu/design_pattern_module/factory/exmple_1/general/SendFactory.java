package com.youshu.design_pattern_module.factory.exmple_1.general;

import com.youshu.design_pattern_module.factory.exmple_1.MailSender;
import com.youshu.design_pattern_module.factory.exmple_1.Sender;
import com.youshu.design_pattern_module.factory.exmple_1.SmsSender;

/**
 * 普通工厂模式:  就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 10:22
 */

public class SendFactory {
    /**
     * 普通工厂模式
     */
    public Sender produce(String type){
        if("mail".equals(type)){
            return new MailSender();
        }else if("sms".equals(type)){
            return new SmsSender();
        }else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }
}
