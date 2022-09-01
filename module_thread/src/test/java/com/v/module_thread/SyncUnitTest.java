package com.v.module_thread;


import org.junit.Test;

public class SyncUnitTest {

    @Test
    public void mainTest() {
        System.out.print("2333333333333333333333333333333333333333333");
        Number1 n1 = new Number1();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n1.b();
        }).start();
    }

    public class Number1 {

        public synchronized void a() {
            System.out.print("1");
        }

        public synchronized void b() {
            System.out.print("2");
        }
    }
}



