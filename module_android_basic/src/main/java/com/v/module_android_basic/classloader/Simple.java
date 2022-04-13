package com.v.module_android_basic.classloader;

public class Simple {

    static {
        System.out.println("Simple will be initialized ");
    }

    public static final int x=10;

    public static String say(){
        return "hello";
    }

}
