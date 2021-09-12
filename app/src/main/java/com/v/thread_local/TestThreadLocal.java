package com.v.thread_local;

import io.reactivex.annotations.Nullable;

public class TestThreadLocal {
    public static void main(String arg[]){
        final ThreadLocal<String> threadLocal=new ThreadLocal<String>(){
            @Nullable
            @Override
            protected String initialValue() {
                return "jett 老师";
            }
        };

        System.out.println("threadLocal.get()--->"+threadLocal.get());

        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                String get1 = threadLocal.get();

                System.out.println("get1--->"+get1);

                threadLocal.set("alen 老师");
                String get2 = threadLocal.get();

                System.out.println("get2--->"+get2);
            }
        };
        thread.start();
    }
}
