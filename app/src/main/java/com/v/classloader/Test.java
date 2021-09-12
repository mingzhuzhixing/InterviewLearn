package com.v.classloader;

public class Test {
    //主动调用
    public static void main(String[] args) {
        //1. new
//        Simple simple = new Simple();

        //2.访问类的静态变量
//        System.out.println(Simple.x);

        //3.调用静态方法
//        System.out.println(Simple.say());

        //4.反射调用
//        try {
//            Class.forName("com.v.classloader.Simple");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        //5.初始化子类会引起父类的主动使用
        System.out.println(Child.m);

        //6、main
    }
}
