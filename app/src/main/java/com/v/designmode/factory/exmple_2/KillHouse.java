package com.v.designmode.factory.exmple_2;

public abstract class KillHouse {

    public abstract Knife createKnife();

    public void KillAnimal(Animal animal){
        // 有一些通用的准备步骤
        commonPrepareWork();
        // 有一些因动物类别而不同的准备步骤需要
        customPrepareWork();

        Knife knife=createKnife();
        knife.chop(animal);
    }

    protected abstract void customPrepareWork();

    private void commonPrepareWork() {

    }
}
