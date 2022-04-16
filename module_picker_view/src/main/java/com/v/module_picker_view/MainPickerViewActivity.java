package com.v.module_picker_view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.v.module_picker_view.dialog.PickerTimePopupWindow;
import com.v.module_picker_view.dialog.SelectLocationPopupWindow;
import com.v.module_picker_view.dialog.SelectTimePopupWindow;
import com.v.module_utils.ScreenUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainPickerViewActivity extends AppCompatActivity {
    private TextView tvSelectTime;
    private TimePickerBuilder pvTime;
    private TextView tvLocation, tvSelectTime3;
    private LinearLayout llContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_view);
        tvSelectTime = findViewById(R.id.tv_select_time);
        tvSelectTime3 = findViewById(R.id.tv_custom_time3);
        tvLocation = findViewById(R.id.tv_location);
        llContainer = findViewById(R.id.ll_container);
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
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                tvSelectTime.setText(sdf.format(date));
            }
        });
        pvTime.setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(20)//滚轮文字大小
                .setTitleSize(14)//标题文字大小
                .setTitleText("选择开始日期")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setItemVisibleCount(5)//显示的个数
                .setDecorView(null)
                //.setLunarCalendar(true) // 阴历
                .setLineSpacingMultiplier(3) //行高
                .setTitleColor(Color.parseColor("#676767"))//标题文字颜色
                .setSubmitColor(Color.parseColor("#14C586"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#676767"))//取消按钮文字颜色
                .setTitleBgColor(0xFFFFFFFF)//标题背景颜色 Night mode
                .setBgColor(0xFFFFFFFF)//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false);//是否显示为对话框样式
        //修复使用PickerView 弹出窗口与手机虚拟导航按键重叠问题
        TimePickerView build = pvTime.build();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) build.getDialogContainerLayout().getLayoutParams();
        layoutParams.bottomMargin =  ScreenUtils.getNavigationBarHeight(this);
        build.getDialogContainerLayout().setLayoutParams(layoutParams);
        build.show();
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

    }

    PickerTimePopupWindow mPickerTimePopupWindow;

    public void customTimePickerView3(View view) {
        mPickerTimePopupWindow = new PickerTimePopupWindow(this, new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.tv_location_submit) {
                    tvSelectTime3.setText(String.format("%s %s %s %s", mPickerTimePopupWindow.getSelectYear(), mPickerTimePopupWindow.getSelectMonth(), mPickerTimePopupWindow.getSelectDay(), mPickerTimePopupWindow.getSelectWeek()));
                    mPickerTimePopupWindow.dismiss();
                }
            }
        });
        mPickerTimePopupWindow.showAtLocation(this.findViewById(R.id.ll_container), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}