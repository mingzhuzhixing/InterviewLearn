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
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.v.module_picker_view.dialog.PickerTimePopupWindow;
import com.v.module_picker_view.dialog.SelectLocationPopupWindow;
import com.v.module_picker_view.helper.LocationHelper;
import com.v.module_utils.ScreenUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainPickerViewActivity extends AppCompatActivity {
    private TextView tvSelectTime, tvSelectTimeNYR, tvSelectTimeSFM;
    private TimePickerBuilder pvTime;
    private TextView tvLocation, tvSelectLocation, tvCustomTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_view);
        tvSelectTime = findViewById(R.id.tv_select_time);
        tvSelectTimeNYR = findViewById(R.id.tv_select_time_ymd);
        tvSelectTimeSFM = findViewById(R.id.tv_select_time_sfm);
        tvSelectLocation = findViewById(R.id.tv_select_location);
        tvLocation = findViewById(R.id.tv_location);
        tvCustomTime = findViewById(R.id.tv_custom_time);
    }

    /**
     * 显示PickerView时间全部选择器
     */
    public void showPickerView(View view) {
        getTimePicker(1, new boolean[]{true, true, true, true, true, true}).show(view);
    }

    /**
     * 显示PickerView时间年月日选择器
     */
    public void showPickerViewYMD(View view) {
        getTimePicker(2, new boolean[]{true, true, true, false, false, false}).show(view);
    }

    /**
     * 显示PickerView时间时分秒选择器
     */
    public void showPickerViewSFM(View view) {
        getTimePicker(3, new boolean[]{false, false, false, true, true, true}).show(view);
    }

    @SuppressLint("SimpleDateFormat")
    private TimePickerView getTimePicker(int type, boolean[] array) {
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
                SimpleDateFormat sdf;
                if (type == 1) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    tvSelectTime.setText(sdf.format(date));
                } else if (type == 2) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    tvSelectTimeNYR.setText(sdf.format(date));
                } else if (type == 3) {
                    sdf = new SimpleDateFormat("HH:mm:ss");
                    tvSelectTimeSFM.setText(sdf.format(date));
                }
            }
        });
        pvTime.setType(array)// 默认全部显示 六个参数分别表示 年 月 日 时 分 秒 true代表显示哪些字段 如果不显示则设置false
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(20)//滚轮文字大小
                .setTitleSize(14)//标题文字大小
                .setTitleText("选择开始日期")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setItemVisibleCount(5)//显示的个数
                //.setLunarCalendar(true) // 阴历
                .setLineSpacingMultiplier(3) //行高
                //.setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
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
        layoutParams.bottomMargin = ScreenUtils.getNavigationBarHeight(this);
        build.getDialogContainerLayout().setLayoutParams(layoutParams);
        return build;
    }

    /**
     * 显示PickerView时间选择器
     */
    public void showLocationPickerView(View view) {
        LocationHelper instance = LocationHelper.getInstance();
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = instance.getProvinceList().get(options1)
                        + instance.getCityList().get(options1).get(option2);
                tvSelectLocation.setText(tx);
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {
                String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("城市选择")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(0xFFFFFFFF)//标题背景颜色 Night mode
                .setBgColor(0xFFFFFFFF)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setItemVisibleCount(7)
                .setLineSpacingMultiplier(3.0f)
                //.setLinkage(false)//设置是否联动，默认true
                .setLabels("省", "市", "区")//设置选择的三级单位
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .isDialog(false)//是否显示为对话框样式
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .build();

        //修复使用PickerView 弹出窗口与手机虚拟导航按键重叠问题
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) pvOptions.getDialogContainerLayout().getLayoutParams();
        layoutParams.bottomMargin = ScreenUtils.getNavigationBarHeight(this);
        pvOptions.getDialogContainerLayout().setLayoutParams(layoutParams);

        pvOptions.setPicker(instance.getProvinceList(), instance.getCityList());//添加数据源
        pvOptions.show();
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

    private PickerTimePopupWindow mPickerTimePopupWindow;

    /**
     * 自定义选择日期时间
     */
    public void customTimePickerView(View view) {
        mPickerTimePopupWindow = new PickerTimePopupWindow(this, new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.tv_location_submit) {
                    tvCustomTime.setText(String.format("%s %s %s %s", mPickerTimePopupWindow.getSelectYear(), mPickerTimePopupWindow.getSelectMonth(), mPickerTimePopupWindow.getSelectDay(), mPickerTimePopupWindow.getSelectWeek()));
                    mPickerTimePopupWindow.dismiss();
                }
            }
        });
        mPickerTimePopupWindow.showAtLocation(this.findViewById(R.id.ll_container), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


}