package com.v.module_thread.thread;

import java.lang.ref.WeakReference;

public abstract class RunWeek<T> implements Runnable {
        protected WeakReference<T> mRef;
        public String mTag;
        public boolean noCheck;
        public RunWeek() {
            noCheck = true;
        }
        @Override
        public void run() {
            if(noCheck) {
                run(null);
            } else if(!isCleaned()){
                run(mRef.get());
            }
            mRef = null;
        }

        public boolean isCleaned(){
            return mRef == null || mRef.get() == null;
        }

        public abstract void run(T t);
    }
