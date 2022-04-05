package com.v.module_dialog.system_dialog;

import android.view.View;

public class ShareChannelBean {

    public int shareType;

    public View.OnClickListener onClickListener;

    public ShareChannelBean(int shareType, View.OnClickListener onClickListener) {
        this.shareType = shareType;
        this.onClickListener = onClickListener;
    }
}
