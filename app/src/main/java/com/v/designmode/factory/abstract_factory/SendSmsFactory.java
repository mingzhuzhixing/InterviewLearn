package com.v.designmode.factory.abstract_factory;

import com.v.designmode.factory.Sender;
import com.v.designmode.factory.SmsSender;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 10:45
 */

public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
