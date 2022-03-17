package com.v.module_proxy.static_proxy;

import com.v.module_proxy.ILawsuit;

/**
 * 具体的诉讼人
 */
public class XiaoMin implements ILawsuit {

    @Override
    public void submit() {
        System.out.println("诉讼申请");
    }


    @Override
    public void burden() {
        System.out.println("进行举证");
    }
}
