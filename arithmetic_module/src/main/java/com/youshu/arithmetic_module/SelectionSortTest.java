package com.youshu.arithmetic_module;

/**
 * 选择排序
 */
public class SelectionSortTest {
    public static void main(String[] args) {
        int[] array = {12, 15, 3, 13, 5, 28, 20};
        int[] arr = selectionSort(array);
        for (int value : arr) {
            System.out.print(value+ " ");
        }
    }

    private static int[] selectionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int n = array.length;
        for (int i = 0; i < n; i++) {

            int minIndex = i;

            for (int j = i; j < n; j++) {
                if (array[j] < array[minIndex]) {//找到最小的数
                    minIndex = j;//将最小数的索引保存
                }
            }
            //第一次和第一个元素替换 接着替换第二个元素，，，，
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
