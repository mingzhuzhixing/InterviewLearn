package com.v.proxy;

import com.v.proxy.dyna_proxy.DynamicProxy;
import com.v.proxy.static_proxy.Lawyer;
import com.v.proxy.static_proxy.XiaoMin;

import java.lang.annotation.Retention;
import java.lang.reflect.Proxy;


/**
 * 客户测试类
 */
public class Client {
    public static void main(String[] args) {
        //构造一个小民出来
//        ILawsuit xiaoMin = new XiaoMin();
////        //构造一个代理律师并将小民作为构造参数传递进去
////        Lawyer lawyer=new Lawyer(xiaoMin);
////        //律师提交诉讼
////        lawyer.submit();
////        //律师进行举证
////        lawyer.burden();
//
////        //获取被代理类小民的ClassLoader
//        ClassLoader classLoader = xiaoMin.getClass().getClassLoader();
////
////        //动态构造一个代理者律师
//        ILawsuit lawyer = (ILawsuit) Proxy.newProxyInstance(classLoader,
//                new Class[]{ILawsuit.class}, new DynamicProxy(xiaoMin));
//        //律师提交诉讼
//        lawyer.submit();
//        //律师进行举证
//        lawyer.burden();
        int[] array = {19, 8, 3, 14, 5, 1, 20};

        //8 19 3 14 5 1 20
        //8 3 19 14 5 1 20
        //

//        int[] charu = getCharu(array);
//        int[] charu = getMaopao(array);
        getQuick(array, 0, array.length-1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i]);
        }
    }

    /**
     * 冒泡排序
     */
    private static int[] getMaopao(int[] array) {
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return array;
    }


    /**
     * 插入排序
     */
    private static int[] getCharu(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                int temp = 0;
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 快速排序
     */
    private static void getQuick(int[] array, int L, int R) {
        if (L >= R) {
            return;
        }

        int left = L;
        int right = R;

        int pivot = array[left]; //基准数
        while (left < right) {
            //先判断右边的数据比较
            while (left < right && array[right] >= pivot) {
                right--;
            }
            if (left < right) {
                array[left] = array[right];
            }

            while (left < right && array[left] <= pivot) {
                left++;
            }

            if (left < right) {
                array[right] = array[left];
            }

            if (left >= right) {
                array[left] = pivot;
            }
        }

        getQuick(array, L, right - 1);
        getQuick(array, right + 1, R);
    }


}
