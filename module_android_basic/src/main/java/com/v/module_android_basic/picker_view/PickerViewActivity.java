package com.v.module_android_basic.picker_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.v.module_android_basic.R;
import java.util.Calendar;

public class PickerViewActivity extends AppCompatActivity {
    private TextView tvSelectTime;
//    private TimePickerBuilder pvTime;
    private TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_view);
        tvSelectTime = findViewById(R.id.tv_select_time);
        tvLocation = findViewById(R.id.tv_location);
    }

    /**
     * 显示选择器
     */
    public void showPickerView(View view) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(2013, 0, 1);
        endDate.set(2020, 11, 31);
//        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                @SuppressLint("SimpleDateFormat")
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                tvSelectTime.setText(sdf.format(date));
//            }
//        });
//        pvTime.setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
//                .setCancelText("Cancel")//取消按钮文字
//                .setSubmitText("Sure")//确认按钮文字
//                .setContentTextSize(18)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                .setTitleText("Title")//标题文字
//                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(true)//是否循环滚动
//                .setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                .setRangDate(startDate, endDate)//起始终止年月日设定
//                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .isDialog(false);//是否显示为对话框样式
//        pvTime.build().show();
    }

    private SelectLocationPopupWindow locationPopupWindow;

    /**
     * 自定义地区选择器
     */
    public void customLocationPickerView(View view) {
        locationPopupWindow = new SelectLocationPopupWindow(this, new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.tv_location_submit) {
                    tvLocation.setText(locationPopupWindow.getProvinceName() + " " + locationPopupWindow.getCityName());
                    locationPopupWindow.dismiss();
                }
            }
        });
        locationPopupWindow.showAtLocation(this.findViewById(R.id.ll_container), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private SelectTimePopupWindow mTimePopupWindow;

    /**
     * 自定义选择日期时间
     */
    public void customTimePickerView(View view) {
        mTimePopupWindow = new SelectTimePopupWindow(this, new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.tv_location_submit) {
                    mTimePopupWindow.dismiss();
                }
            }
        });
        mTimePopupWindow.showAtLocation(this.findViewById(R.id.ll_container), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    public void customTimePickerView2(View view) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);
        int currentYear = selectedDate.get(Calendar.YEAR);
        //正确设置方式 原因：注意事项有说明
        startDate.set(currentYear, selectedDate.get(Calendar.MONTH) + 1, selectedDate.get(Calendar.DAY_OF_MONTH));
        endDate.set(currentYear + 2, 11, 31);
//        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                @SuppressLint("SimpleDateFormat")
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                tvSelectTime.setText(sdf.format(date));
//            }
//        });
//        pvTime.setCancelText("Cancel")//取消按钮文字
//                .setSubmitText("Sure")//确认按钮文字
//                .setContentTextSize(18)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                .setTitleText("Title")//标题文字
//                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(true)//是否循环滚动
//                .setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                .setRangDate(startDate, endDate)//起始终止年月日设定
//                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .isDialog(false);//是否显示为对话框样式
//        pvTime.build().show();
    }

    PickerTimePopupWindow mPickerTimePopupWindow;

    public void customTimePickerView3(View view) {
        mPickerTimePopupWindow = new PickerTimePopupWindow(this, new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.tv_location_submit) {
                    mPickerTimePopupWindow.dismiss();
                }
            }
        });
        mPickerTimePopupWindow.showAtLocation(this.findViewById(R.id.ll_container), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}