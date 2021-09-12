package com.youshu.design_pattern_module.factory.exmple_2;

public class KillCowHouse extends KillHouse {
    @Override
    public Knife createKnife() {
        return new KillCowKnife();
    }

    @Override
    protected void customPrepareWork() {

    }
}
