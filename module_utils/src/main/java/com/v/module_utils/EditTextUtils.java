package com.v.module_utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class EditTextUtils {

    /**
     * 收回软键盘
     */
    public static void hideSoftInput(EditText editText, Context context) {
        try {
            InputMethodManager imm = (InputMethodManager) (context.getSystemService(
                    Context.INPUT_METHOD_SERVICE));
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹起软键盘
     */
    public static void showSoftInput(final EditText editText, Context context) {
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    editText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
                    editText.setSelection(editText.getText().toString().length());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },600);
    }

//
//    /**
//     * 弹起软键盘
//     */
//    public static void showSoftInput(final EditText editText, Context context, SupportFragment fragment) {
//        editText.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    if(fragment !=null && !fragment.isDestroyed){
//                        editText.requestFocus();
//                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
//                        editText.setSelection(editText.getText().toString().length());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },600);
//    }




    public static String getJsonString(String s) {
        char[] temp = s.toCharArray();
        int n = temp.length;
        for (int i = 0; i < n; i++) {
            if (temp[i] == ':' && temp[i + 1] == '"') {
                for (int j = i + 2; j < n; j++) {
                    if (temp[j] == '"') {
                        if (temp[j + 1] != ',' && temp[j + 1] != '}') {
                            temp[j] = '”';
                        } else if (temp[j + 1] == ',' || temp[j + 1] == '}') {
                            break;
                        }
                    }
                }
            }
        }
        return new String(temp);
    }

    public static String getString(String s) {
        char[] temp = s.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == '"') {
                temp[i] = '＂';
            }
        }
        return new String(temp);
    }

}
