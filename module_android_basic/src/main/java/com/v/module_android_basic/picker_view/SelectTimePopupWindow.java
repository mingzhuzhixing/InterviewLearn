package com.v.module_android_basic.picker_view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.v.module_android_basic.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**
 * 滚轮
 *
 * @author lxq
 */
public class SelectTimePopupWindow extends PopupWindow {
    private static final String TAG = "SelectTimePopupWindow";
    private final TextView tv_location_title;
    private final ScrollerNumberPicker snp_popupwindow_year;
    private final ScrollerNumberPicker snp_popupwindow_month;
    private final ScrollerNumberPicker snp_popupwindow_day;

    private final ArrayList<String> mYearList = new ArrayList<String>();
    private final ArrayList<String> mMonthList = new ArrayList<String>();
    private final ArrayList<String> mDayList = new ArrayList<String>();
    private final ArrayList<String> mWeekList = new ArrayList<String>();
    private final int mMaxMonth = 12;

    //当前选择的年
    private String mCurSelectYear;
    //当前选择的月
    private String mCurSelectMonth;

    //当前时间的年份
    private int mCurrentYear;
    //当前时间的月份
    private int mCurrentMonth;
    //今天
    private int mCurrentDay;
    //当前时间的星期
    private int mCurrentWeek;

    /**
     * context 上下文
     * <p>
     * itemsOnClick 点击事件
     */
    public SelectTimePopupWindow(Context context, OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.layout_popupwindow_time, null);

        tv_location_title = mView.findViewById(R.id.tv_location_title);
        snp_popupwindow_year = mView.findViewById(R.id.snp_popupwindow_year);
        snp_popupwindow_month = mView.findViewById(R.id.snp_popupwindow_month);
        snp_popupwindow_day = mView.findViewById(R.id.snp_popupwindow_day);

        // 取消按钮
        TextView tv_location_cancel = mView.findViewById(R.id.tv_location_cancel);
        tv_location_cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
        RelativeLayout relativeLayout = mView.findViewById(R.id.rl_popupwindow_layout);
        relativeLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });

        // 设置按钮监听
        TextView tv_location_submit = mView.findViewById(R.id.tv_location_submit);
        tv_location_submit.setOnClickListener(itemsOnClick);

        initData();

        // 设置SelectPicPopupWindow的View
        this.setContentView(mView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }

    public void setTitle(String string) {
        tv_location_title.setText(string);
    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        mCurrentYear = calendar.get(Calendar.YEAR);
        mCurrentMonth = calendar.get(Calendar.MONTH) + 1;
        mCurrentDay = calendar.get(Calendar.DAY_OF_MONTH);
        mCurrentWeek = calendar.get(Calendar.DAY_OF_WEEK);

        initYearData();
        initMonthData();
        initDayData();

        // 选择事件
        snp_popupwindow_year.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {

            @Override
            public void endSelect(int id, String year) {
                //Log.i(TAG, "year endSelect text:" + text + " id:" + id);
                mCurSelectYear = !TextUtils.isEmpty(year) ? year : mCurSelectYear;
                if (id == 0) {
                    initMonthData();
                } else {
                    updateMonthData();
                }
                updateDayData(mCurSelectYear, mCurSelectMonth);
            }

            @Override
            public void selecting(int id, String year) {

            }
        });

        snp_popupwindow_month.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {

            @Override
            public void endSelect(int id, String month) {
//                LogUtils.i(TAG, "month endSelect text:" + text);
                updateDayData(mCurSelectYear, month);
            }

            @Override
            public void selecting(int id, String month) {
            }

        });

        snp_popupwindow_day.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {

            @Override
            public void endSelect(int id, String day) {
                //LogUtils.i(TAG, "day endSelect text:" + text);
            }

            @Override
            public void selecting(int id, String day) {
            }

        });
    }

    /**
     * 初始化年数据
     */
    private void initYearData() {
        mYearList.clear();
        mCurSelectYear = mCurrentYear + "";
        mYearList.add(mCurrentYear + "");
        mYearList.add((mCurrentYear + 1) + "");
        // 放数据
        snp_popupwindow_year.setData(mYearList);
        snp_popupwindow_year.setDefault(0);
    }

    /**
     * 初始化月
     */
    private void initMonthData() {
        mMonthList.clear();
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        mCurSelectMonth = currentMonth + "";
        for (int i = currentMonth; i <= mMaxMonth; i++) {
            mMonthList.add(i + "");
        }
        // 放数据
        snp_popupwindow_month.setData(mMonthList);
        snp_popupwindow_month.setDefault(0);
    }

    /**
     * 更新月
     */
    public void updateMonthData() {
        mMonthList.clear();
        mCurSelectMonth = "1";
        for (int i = 1; i <= mMaxMonth; i++) {
            mMonthList.add(i + "");
        }
        snp_popupwindow_month.setData(mMonthList);
        snp_popupwindow_month.setDefault(0);
    }

    /**
     * 初始化天数
     */
    private void initDayData() {
        mDayList.clear();
        int currentDayCount = getDays(mCurrentYear, mCurrentMonth);
        for (int i = mCurrentDay; i <= currentDayCount; i++) {
            String result = getWeek(mCurrentYear, mCurrentMonth, i);
            mDayList.add(i + " " + result);
        }
        // 放数据
        snp_popupwindow_day.setData(mDayList);
        snp_popupwindow_day.setDefault(0);
    }

    /**
     * 更新天数
     */
    private void updateDayData(String currentYear, String currentMonth) {
        mDayList.clear();
        int year = !TextUtils.isEmpty(currentYear) ? Integer.parseInt(currentYear) : 0;
        int month = !TextUtils.isEmpty(currentYear) ? Integer.parseInt(currentMonth) : 0;
        int currentDayCount = getDays(year, month);
        int currentDay = year == mCurrentYear && month == mCurrentMonth ? mCurrentDay : 1;
        Log.i(TAG, "month updateDayData  currentDay:" + currentDay + " currentDayCount:" + currentDayCount + " year" + year + " month:" + month);
        for (int i = currentDay; i <= currentDayCount; i++) {
            String result = getWeek(mCurrentYear, mCurrentMonth, i);
            mDayList.add(i + " " + result);
        }

        Collections.sort(mDayList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String s1 = o1.toString().split(" ")[0];
                String s2 = o2.toString().split(" ")[0];
                int diff = Integer.parseInt(s2) - Integer.parseInt(s1);
                if (diff > 0) {
                    return -1;
                } else if (diff < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (int i = 0; i < mDayList.size(); i++) {
            Log.i(TAG, "month updateDayData  i:" + i + " mDayList.get(i):" + mDayList.get(i));
        }
        // 放数据
        snp_popupwindow_day.setData(mDayList);
        snp_popupwindow_day.setDefault(0);
    }

    /**
     * 得到指定月的天数
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        //LogUtils.i(TAG, "maxDate:" + maxDate);
        return maxDate;
    }

    //返回当月天数
    private int getDays(int year, int month) {
        int days;
        int FebDay = 28;
        if (isLeap(year))
            FebDay = 29;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;
    }

    /**
     * 判断闰年
     */
    boolean isLeap(int year) {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    private String getWeek(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        String result = "";
        switch (week) {
            case 1:
                result = "星期天";
                break;
            case 2:
                result = "星期一";
                break;
            case 3:
                result = "星期二";
                break;
            case 4:
                result = "星期三";
                break;
            case 5:
                result = "星期四";
                break;
            case 6:
                result = "星期五";
                break;
            case 7:
                result = "星期六";
                break;
        }
        return result;
    }
}
