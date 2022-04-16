package com.v.module_picker_view.wheel_view;

import java.util.List;

public class ArrayWheelAdapter<T> implements WheelAdapter {
    private List<T> items;

    public ArrayWheelAdapter() {
    }

    public ArrayWheelAdapter(List<T> items) {
        this.items = items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Object getItem(int index) {
        return index >= 0 && index < this.items.size() ? this.items.get(index) : "";
    }

    public int getItemsCount() {
        return this.items.size();
    }

    public int indexOf(Object o) {
        return this.items.indexOf(o);
    }
}
