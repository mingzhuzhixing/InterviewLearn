package com.v.module_dialog.autoservice;

import android.content.Context;
import android.content.Intent;

import com.google.auto.service.AutoService;
import com.v.common_module.autoservice.IDialogService;
import com.v.module_dialog.DialogMainActivity;

/**
 * ClassName: DialogServiceImpl
 * Description:
 *
 * @author zhuming
 * @package_name com.v.dialog_module.autoservice
 * @date 2022/1/27 4:36 下午
 */
@AutoService(IDialogService.class)
public class DialogServiceImpl implements IDialogService {
    @Override
    public void startDialogActivity(Context context) {
        context.startActivity(new Intent(context, DialogMainActivity.class));
    }
}
