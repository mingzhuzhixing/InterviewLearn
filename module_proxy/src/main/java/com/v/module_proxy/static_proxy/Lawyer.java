package com.v.module_proxy.static_proxy;


import com.v.module_proxy.ILawsuit;

/**
 * 代理律师
 */
public class Lawyer implements ILawsuit {
    private ILawsuit lawsuit; //持有一个具体被代理者的引用

    public Lawyer(ILawsuit lawsuit) {
        this.lawsuit = lawsuit;
    }

    @Override
    public void submit() {
        lawsuit.submit();
    }

    @Override
    public void burden() {
        lawsuit.burden();
    }
}
