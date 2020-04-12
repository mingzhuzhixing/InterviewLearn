package com.v.designmode.factory.exmple_1.abstract_factory;

import com.v.designmode.factory.exmple_1.MailSender;
import com.v.designmode.factory.exmple_1.Sender;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 10:46
 */

public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
