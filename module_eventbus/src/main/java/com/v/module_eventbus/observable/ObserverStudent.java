package com.v.module_eventbus.observable;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * ClassName: ObserverStudent
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_eventbus.observable
 * @date 2023/3/4 22:22
 */
public class ObserverStudent implements Observer {

    private String name;

    public ObserverStudent(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.i("zm1234", "update: " + name + "" + arg);
    }
}
