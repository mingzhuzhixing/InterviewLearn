package com.v.module_thread.blocking_queue;

/**
 * ClassName: CPriorityBlockingQueue
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_thread.blocking_queue
 * @date 2022/6/1 9:53
 */
public class CPriorityBlockingQueue {
    private CPriorityBlockingQueue instance;

    public CPriorityBlockingQueue getInstance(){
        return SubFactory.mQueue;
    }

    private static class SubFactory{
        static CPriorityBlockingQueue mQueue = new CPriorityBlockingQueue();
    }

    public void init(){

    }

}
