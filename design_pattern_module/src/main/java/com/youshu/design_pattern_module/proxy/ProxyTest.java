package com.youshu.design_pattern_module.proxy;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 14:34
 */

public class ProxyTest {

    public static void main(String[] args) {
        Sourceable source = new Proxy();
        source.method();
    }

    /**
     *
     * 代理模式的应用场景：

         如果已有的方法在使用的时候需要对原有的方法进行改进，此时有两种办法：

         1、修改原有的方法来适应。这样违反了“对扩展开放，对修改关闭”的原则。

         2、就是采用一个代理类调用原有的方法，且对产生的结果进行控制。这种方法就是代理模式。

         使用代理模式，可以将功能划分的更加清晰，有助于后期维护！
     *
     */
}
