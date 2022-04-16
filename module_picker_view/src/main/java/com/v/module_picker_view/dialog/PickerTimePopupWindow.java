package com.v.module_picker_view.dialog;

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

import com.v.module_picker_view.R;
import com.v.module_picker_view.wheel_view.ArrayWheelAdapter;
import com.v.module_picker_view.wheel_view.OnItemSelectedListener;
import com.v.module_picker_view.wheel_view.WheelView;
import com.v.module_utils.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 滚轮
 *
 * @author lxq
 */
public class PickerTimePopupWindow extends PopupWindow {
    private static final String TAG = "SelectTimePopupWindow";
    private final TextView tv_location_title;
    private final WheelView snp_popupwindow_year;
    private final WheelView snp_popupwindow_month;
    private final WheelView snp_popupwindow_day;

    private final ArrayList<String> mYearList = new ArrayList<String>();
    private final ArrayList<String> mMonthList = new ArrayList<String>();
    private final ArrayList<String> mDayList = new ArrayList<String>();
    private final int mMaxMonth = 12;
    private ArrayWheelAdapter mYearAdapter;
    private ArrayWheelAdapter mMonthAdapter;
    private ArrayWheelAdapter mDayAdapter;

    //当前选择的年 2003
    private String mCurSelectYear;
    //当前选择的月 23
    private String mCurSelectMonth;
    //当前选择的日
    private String mCurSelectDay;

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
     * itemsOnClick 点击事件
     */
    public PickerTimePopupWindow(Context context, OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.layout_popupwindow_picker_time, null);

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
    }

    /**
     * 初始化年数据
     */
    private void initYearData() {
        mYearList.clear();
        mCurSelectYear = mCurrentYear + "";
        mYearList.add(mCurrentYear + "年");
        mYearList.add((mCurrentYear + 1) + "年");
        snp_popupwindow_year.setCyclic(false);
        snp_popupwindow_year.setCurrentItem(0);
        if (mYearAdapter == null) {
            mYearAdapter = new ArrayWheelAdapter(mYearList);
        }
        snp_popupwindow_year.setAdapter(mYearAdapter);
        snp_popupwindow_year.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                //Log.i(TAG, "" + mYearList.get(index));
                if (index == 0) {
                    initMonthData();
                } else {
                    updateMonthData();
                }
                updateDayData(mYearList.get(index), mCurSelectMonth);
            }
        });
    }

    /**
     * 初始化月
     */
    private void initMonthData() {
        snp_popupwindow_month.setCyclic(false);
        snp_popupwindow_month.setCurrentItem(0);
        if (mMonthAdapter == null) {
            mMonthAdapter = new ArrayWheelAdapter();
        }

        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        mCurSelectMonth = currentMonth + "";
        mMonthList.clear();
        for (int i = currentMonth; i <= mMaxMonth; i++) {
            mMonthList.add(i + "月");
        }
        mMonthAdapter.setItems(mMonthList);
        snp_popupwindow_month.setAdapter(mMonthAdapter);
        snp_popupwindow_month.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                //Log.i(TAG, "" + mMonthList.get(index));
                updateDayData(mCurSelectYear, mMonthList.get(index));
            }
        });
    }

    /**
     * 更新月
     */
    public void updateMonthData() {
        mMonthList.clear();
        mCurSelectMonth = "1";
        for (int i = 1; i <= mMaxMonth; i++) {
            mMonthList.add(i + "月");
        }
        mMonthAdapter.setItems(mMonthList);
        snp_popupwindow_month.setCurrentItem(0);
        snp_popupwindow_month.setAdapter(mMonthAdapter);
    }

    /**
     * 初始化天数
     */
    private void initDayData() {
        mDayList.clear();
        int currentDayCount = getDays(mCurrentYear, mCurrentMonth);
        mCurSelectDay = mCurrentDay + "";
        for (int i = mCurrentDay; i <= currentDayCount; i++) {
            String week = getWeek(mCurrentYear, mCurrentMonth, i);
            mDayList.add(i + "日" + week);
        }
        snp_popupwindow_day.setCyclic(false);
        snp_popupwindow_day.setCurrentItem(0);
        if (mDayAdapter == null) {
            mDayAdapter = new ArrayWheelAdapter(mDayList);
        }
        snp_popupwindow_day.setAdapter(mDayAdapter);
        snp_popupwindow_day.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                //Log.i(TAG, "" + mDayList.get(index));
                String dayAndWeek = mDayList.get(index);
                if (!TextUtils.isEmpty(dayAndWeek) && dayAndWeek.contains("日")) {
                    String[] split = dayAndWeek.split("日");
                    mCurSelectDay = split[0];
                } else {
                    mCurSelectDay = dayAndWeek;
                }
            }
        });
    }

    /**
     * 更新天数
     */
    private void updateDayData(String currentYear, String currentMonth) {
        mDayList.clear();
        if (!TextUtils.isEmpty(currentYear) && currentYear.contains("年")) {
            currentYear = currentYear.substring(0, currentYear.indexOf("年"));
        }
        mCurSelectYear = currentYear;
        if (!TextUtils.isEmpty(currentMonth) && currentMonth.contains("月")) {
            currentMonth = currentMonth.substring(0, currentMonth.indexOf("月"));
        }
        mCurSelectMonth = currentMonth;
        int year = !TextUtils.isEmpty(currentYear) ? Integer.parseInt(currentYear) : 0;
        int month = !TextUtils.isEmpty(currentYear) ? Integer.parseInt(currentMonth) : 0;
        int currentDayCount = getDays(year, month);
        int currentDay = year == mCurrentYear && month == mCurrentMonth ? mCurrentDay : 1;
        mCurSelectDay = currentDay + "";
        //Log.i(TAG, "month updateDayData  currentDay:" + currentDay + " currentDayCount:" + currentDayCount + " year" + year + " month:" + month);
        for (int i = currentDay; i <= currentDayCount; i++) {
            String week = getWeek(year, month, i);
            mDayList.add(i + "日" + week);
        }
        mDayAdapter.setItems(mDayList);
        snp_popupwindow_day.setCurrentItem(0);
        snp_popupwindow_day.setAdapter(mDayAdapter);
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
                result = "(周天)";
                break;
            case 2:
                result = "(周一)";
                break;
            case 3:
                result = "(周二)";
                break;
            case 4:
                result = "(周三)";
                break;
            case 5:
                result = "(周四)";
                break;
            case 6:
                result = "(周五)";
                break;
            case 7:
                result = "(周六)";
                break;
        }
        return result;
    }

    public String getSelectYear() {
        return mCurSelectYear;
    }

    public String getSelectMonth() {
        return mCurSelectMonth;
    }

    public String getSelectDay() {
        return mCurSelectDay;
    }

    public String getSelectWeek() {
        if (TextUtils.isEmpty(mCurSelectYear) || TextUtils.isEmpty(mCurSelectMonth) || TextUtils.isEmpty(mCurSelectDay)) {
            return "";
        }
        int year, month, day;
        if (StringUtils.checkIsNum(mCurSelectYear)) {
            year = Integer.parseInt(mCurSelectYear);
        } else {
            return "";
        }
        if (StringUtils.checkIsNum(mCurSelectMonth)) {
            month = Integer.parseInt(mCurSelectMonth);
        } else {
            return "";
        }
        if (StringUtils.checkIsNum(mCurSelectDay)) {
            day = Integer.parseInt(mCurSelectDay);
        } else {
            return "";
        }
        return getWeek(year, month, day);
    }
}
