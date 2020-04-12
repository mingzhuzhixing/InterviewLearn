package com.v.designmode.builder;

import com.v.designmode.factory.exmple_1.MailSender;
import com.v.designmode.factory.exmple_1.Sender;
import com.v.designmode.factory.exmple_1.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式：工厂类模式提供的是创建单个类的模式，而建造者模式则是将各种产品集中起来进行管理，用来创建复合对象，所谓复合对象就是指某个类具有不同的属性，其实建造者模式就是前面抽象工厂模式和最后的Test结合起来得到的
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 11:19
 */

public class Builder {
    private List<Sender> mList = new ArrayList<>();

    public void produceMailSender(int count){
        for (int  i = 0; i < count; i++){
            mList.add(new MailSender());
        }
    }

    public void produceSmsSender(int count){
        for (int i = 0; i < count; i++){
            mList.add(new SmsSender());
        }
    }
}
