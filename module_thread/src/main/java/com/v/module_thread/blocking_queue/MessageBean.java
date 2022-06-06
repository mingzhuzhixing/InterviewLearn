package com.v.module_thread.blocking_queue;

/**
 * ClassName: MessageBean
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_thread.bean
 * @date 2022/5/31 18:48
 */
public class MessageBean implements Comparable<MessageBean>{
    public String message;
    public long time;
    public int random;

    public MessageBean(String message,long time){
        this.message = message;
        this.time = time;
    }

    public MessageBean(String message,long time,int random){
        this.message = message;
        this.time = time;
        this.random = random;
    }

    @Override
    public int compareTo(MessageBean o) {
        return (int) (this.time - o.time);
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "message='" + message + '\'' +
                ", time=" + time +
                ", random=" + random +
                '}';
    }
}
