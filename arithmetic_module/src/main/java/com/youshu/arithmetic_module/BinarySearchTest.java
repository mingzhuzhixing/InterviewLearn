package com.youshu.arithmetic_module;


/**
 * 二分查找
 */
public class BinarySearchTest {
    public static void main(String[] args) {
        int[] arrays = new int[]{2, 8, 12, 18, 20, 25, 30, 37, 41, 49, 61};
        int key = 8;
        int low = 0;
        int high = arrays.length - 1;
        //非递归查找
        int i = unRecursiveBinarySearch(arrays, key);
        System.out.println("非递归查找，索引值：" + i);

        //递归查找
        int recursiveInt = recursiveBinarySearch(arrays, low, high, key);
        System.out.println("递归查找，索引值：" + recursiveInt);
    }


    /***
     * 二分查找 递归查询
     * @param arrays 要找的有序数组
     * @param low 头指针初始位置
     * @param high 尾指针初始位置
     * @param key 要查找的数
     * @return
     */
    public static int recursiveBinarySearch(int[] arrays, int low, int high, int key) {
        //计算中间的索引值
        int mid = low + (high - low) / 2;
        //判断查找的数是否炸数组中
        //如果此处不加判断，则有可能报 java.lang.StackOverflowError栈内存溢出
        if (low > high || key > arrays[high] || key < arrays[low]) {
            return -1;
        }

        if (arrays[mid] > key) {
            //mid所有对应的值比key大，key应该炸左边区域
            return recursiveBinarySearch(arrays, low, mid - 1, key);
        } else if (arrays[mid] < key) {
            //mid所对应的值比key小，key应该炸右边区域
            return recursiveBinarySearch(arrays, mid + 1, high, key);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找 非递归查询
     *
     * @param arrays 要找的有序数组
     * @param key 要查找的数
     * @return
     */
    public static int unRecursiveBinarySearch(int[] arrays, int key) {
        //头指针初始位置
        int low = 0;
        //尾指针初始位置
        int high = arrays.length - 1;
        //如果此处不加判断，则有可能报 java.lang.StackOverflowError栈内存溢出
        if (low > high || key > arrays[high] || key < arrays[low]) {
            return -1;
        }

        //确保不会出现重复查询。越界
        while (low <= high) {
            //计算出中间索引值。防止溢出
            int mid = (high + low) >>> 1;
            if (key == arrays[mid]) {
                return mid;
            } else if (key < arrays[mid]) {
                high = mid - 1;//mid所对应的值不key大，key应该在左边区域
            } else {
                low = mid + 1;//mid所对应的值比key小，key应该在右边区域
            }
        }
        //若没有，则返回-1
        return -1;
    }
}
