package com.v.module_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.v.module_dialog.fragment.CustomDialogFragment;
import com.v.module_dialog.toast.ToastUtils;
import com.v.module_utils.EditTextUtils;

public class EditTextDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_dialog);
        EditText editText = findViewById(R.id.edit_text);
        EditTextUtils.showSoftInput(editText, this);

        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextUtils.hideSoftInput(editText,EditTextDialogActivity.this);
                showCustomDialog2();
            }
        });
    }

    private void showCustomDialog2() {
        final CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.setFragmentManager(getSupportFragmentManager());
        dialog.setTitle("这个是是标题");
        dialog.setContent("这个是弹窗的内容");
        dialog.setCancelContent("取消");
        dialog.setOkContent("确定");
        dialog.setDimAmount(0.0f);
        dialog.setTag("BottomDialog");
        dialog.setCancelOutside(true);
        dialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragment.dismissDialogFragment();
                ToastUtils.showRoundRectToast("取消了");
            }
        });
        dialog.setOkListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragment.dismissDialogFragment();
                ToastUtils.showRoundRectToast("确定了");
            }
        });
        //这个高度可以自己设置，十分灵活
        //dialog.setHeight(getScreenHeight() / 2);
        dialog.show();
    }
}