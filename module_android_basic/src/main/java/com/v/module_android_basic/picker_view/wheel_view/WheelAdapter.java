package com.v.module_android_basic.picker_view.wheel_view;

public interface WheelAdapter<T> {
    int getItemsCount();

    T getItem(int var1);

    int indexOf(T var1);
}
