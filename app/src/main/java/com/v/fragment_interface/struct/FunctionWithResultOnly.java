package com.v.fragment_interface.struct;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-24 18:21
 */

public abstract class  FunctionWithResultOnly<Result> extends Function {
    public FunctionWithResultOnly(String funcName) {
        super(funcName);
    }

    public abstract Result funtion();
}
