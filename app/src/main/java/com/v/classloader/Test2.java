package com.v.classloader;

public class Test2 {
    //被动使用
    public static void main(String[] args) {
        //1.对象数组
//        Simple[] simples=new Simple[10];
//        System.out.println(simples.length);


        //2.调用静态常量
        System.out.println(Simple.x);
    }
}
