package com.v.handler;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-16 18:18
 */

public class Message {
    Handler target;
    Object obj;


    public Message(){

    }

    @Override
    public String toString() {

        return obj.toString();
    }
}
