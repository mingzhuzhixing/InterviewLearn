package com.youshu.arithmetic_module;

public class ShellSortTest {
    public static void main(String[] args) {
        int[] array = {12, 15, 3, 13, 5, 28, 20};
        int[] arr = shellSort(array);
        for (int value : arr) {
            System.out.print(value+" ");
        }
    }


    /**
     * 希尔排序
     *
     * @param array
     * @return
     */
    public static int[] shellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

}
