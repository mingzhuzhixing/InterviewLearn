package com.v.designmode.factory;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 10:21
 */

public class MailSender implements Sender {

    @Override
    public void send() {
        System.out.println("this is mailsender!");
    }
}
