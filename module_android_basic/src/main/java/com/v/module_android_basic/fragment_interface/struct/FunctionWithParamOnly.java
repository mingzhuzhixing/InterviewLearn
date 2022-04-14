package com.v.module_android_basic.fragment_interface.struct;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-24 18:21
 */

public abstract class FunctionWithParamOnly<Param> extends Function {
    public FunctionWithParamOnly(String funcName) {
        super(funcName);
    }

    public abstract void funtion(Param param);
}
