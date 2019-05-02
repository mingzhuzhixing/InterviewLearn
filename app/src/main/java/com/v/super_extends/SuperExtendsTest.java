package com.v.super_extends;

import java.util.List;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-16 14:53
 */

public class SuperExtendsTest {

//    public void test1(List<Fruit> fruits) {
//        for (Fruit fruit: fruits) {
//            fruit.call();
//        }
//    }
//
//    public static void main(String[] args) {
//
//        List<Apple> apples = new ArrayList<>();
//        List<Fruit> fruits = apples;     //类型转换失败
//        Test test = new Test();
//        test.test1(fruits);    //失败
//    }




    public void test1(List<? extends Fruit> fruits) {
        for (Fruit fruit: fruits) {
            fruit.call();
        }
    }

    public static void main(String[] args) {
        superTest();
    }

    private static void superTest(){

//        下界<? super T>不影响往里存，但往外取只能放在Object对象里
        //一个能放水果以及一切是水果基类的盘子
//        List<? super Fruit> fru=new ArrayList<>();
//        fru.add(new Apple());
//        fru.add(new Fruit());

//        fru.add(new Food());
//        fru.add(new Object());
//        Apple a=fru.get(0);
//        Fruit f=fru.get(0);
//        Object o=fru.get(0);

        Plate<? super Fruit> p1=new Plate<>(new Fruit());
        p1.set(new Fruit());
        p1.set(new Apple());

        Plate<? super Fruit> p2=new Plate<>(new Food());

    }

    private static void extendsTest(){
//        上界<? extends T>不能往里存，只能往外取
//        一个能放水果以及一切是水果派生类的盘子
//        List<? extends Fruit> fru2=new ArrayList<>();
//        Apple apple = (Apple) fru2.get(0);
//        Object o = fru2.get(0);



        Plate<? extends Fruit> fri3=new Plate<Fruit>(new Fruit());
        fri3.get().call();


        Plate<? extends Fruit> fri4=new Plate<Fruit>(new Apple());
        fri4.get().call();

        Plate<? extends Apple> fri5=new Plate<Apple>(new Apple());

        Plate<? extends Banana> fri6=new Plate<Banana>(new Banana());

        Plate<? extends Fruit> fri7=new Plate<Fruit>();
        //不能存入任何元素
//        fri7.set(new Apple()); //Error

    }

}
