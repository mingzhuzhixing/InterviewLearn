package com.v.designmode.factory.abstract_factory;

import com.v.designmode.factory.MailSender;
import com.v.designmode.factory.Sender;

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
