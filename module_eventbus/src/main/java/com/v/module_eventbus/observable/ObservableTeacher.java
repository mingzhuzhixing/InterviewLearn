package com.v.module_eventbus.observable;

import android.util.Log;

import java.util.Observable;

/**
 * ClassName: ObservableTeacher
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_eventbus.observable
 * @date 2023/3/4 22:20
 */
public class ObservableTeacher extends Observable {

    public void publishMessage(String message) {
        // mark as value changed
        Log.i("zm1234", "publishMessage: message: "+message);
        setChanged();
        // trigger notification
        notifyObservers(message);
    }
}
