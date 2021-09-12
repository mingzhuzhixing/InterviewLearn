package com.youshu.design_pattern_module.factory.exmple_1.abstract_factory;

import com.youshu.design_pattern_module.factory.exmple_1.Sender;
import com.youshu.design_pattern_module.factory.exmple_1.SmsSender;

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
