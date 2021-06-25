package com.youshu.design_pattern_module.decorator;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 11:46
 */

public class DecoratorTest {

    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable obj = new Decorator(source);
        obj.method();
    }


    /**
     *
     * 装饰器模式的应用场景：
         1、需要扩展一个类的功能。

         2、动态的为一个对象增加功能，而且还能动态撤销。（继承不能做到这一点，继承的功能是静态的，不能动态增删。）

         缺点：产生过多相似的对象，不易排错！
     */

}
