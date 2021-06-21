package com.v.classloader;

public class Child extends Simple {

    static {
        System.out.println("Child will be initialized ");
    }

    public static int m=20;
}
