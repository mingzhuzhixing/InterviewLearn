package com.youshu.arithmetic_module;

public class InsertionSortTest {
    public static void main(String[] args) {
        int[] array = {12, 15, 3, 13, 5, 28, 20};
        int[] arr = insertionSort(array);
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }

    /**
     * 插入排序
     *
     * @param array
     * @return
     */
    private static int[] insertionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }
}
