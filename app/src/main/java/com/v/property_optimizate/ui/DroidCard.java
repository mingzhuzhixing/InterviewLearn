package com.v.property_optimizate.ui;

import android.content.res.Resources;

public class DroidCard {

    private Resources resources;

    private int drawableId;

    private  int left;

    public DroidCard(Resources resources, int drawableId, int left) {
        this.resources = resources;
        this.drawableId = drawableId;
        this.left = left;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }
}
