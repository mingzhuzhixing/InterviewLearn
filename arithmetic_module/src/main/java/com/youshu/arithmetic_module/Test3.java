package com.youshu.arithmetic_module;

import java.util.Arrays;

/**
 * 给定一个数跟数组，将小于等于该数的数组元素放在左边，将大于该数的数组元素放在右边
 *
 * [4, 3, 1, 2, 5, 9, 8, 6]
 */
public class Test3 {
    public static void main(String[] args) {
        int a[] = {4, 3, 6, 9, 1, 2, 8,5};
        smallLeftBigRight(a, 5);
        System.out.println(Arrays.toString(a));
    }

    public static void smallLeftBigRight(int a[], int num) {
        int i = 0;
        int x = -1;
        while (i < a.length) {
            if (a[i] <= num) {
                x++;
                int temp = a[i];
                a[i] = a[x];
                a[x] = temp;
            }
            i++;
        }
    }
}
