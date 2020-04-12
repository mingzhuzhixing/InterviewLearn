package com.v.arithmetic;


/**
 * 冒泡排序
 *
 * @author zhuming
 * @date 2019-04-17 10:39
 */

public class BubbleSortTest {

    public static void main(String[] args) {
        int[] array = {12, 15, 3, 13, 5, 28, 20};

        int[] arr = dub(array);
        for (int value : arr) {
            System.out.print(value + " ");
        }

        //optimizeDubCre(array);
    }



    private static int[] dub(int[] array) {
        //12, 15, 3, 13, 5, 28, 20
        /**
         * i=0 j=1 j<7
         *     j=2 j<7
         *     j=3 j<7
         *     j=4 j<7
         *     j=5 j<7
         *     j=6 j<7
         *
         * i=1 j=2 j<7
         *     j=3 j<7
         *     j=4 j<7
         *     j=5 j<7
         *     j=6 j<7g67
         *
         * i=2 j=3 j<7
         *     j=4 j<7
         *     j=5 j<7
         *     j=6 j<7
         *
         * i=3 j=4 j<7
         *     j=5 j<7
         *     j=6 j<7
         *
         * i=4 j=5 j<7
         *     j=6 j<7
         *
         * i=5 j=6 j<7
         *
         * i=6 j
         */
//        for(int i = 0; i < n; i++){
//            for(int j = i+1; j < n; j++){
//                if(array[i] > array[j]){
//                    int temp = array[i];
//                    array[i] = array[j];
//                    array[j] = temp;
//                }
//            }
//        }

        /**
         * 原始：12, 15, 3, 13, 5, 28, 20
         *
         * n=7
         * i=6 j=0 j<6
         *     j=1 j<6
         *     j=2
         *     j=3
         *     j=4
         *     j=5
         * 结果：12, 3, 13, 5, 15, 20, 28
         *
         * i=5 j=0 j<5
         *     j=1
         *     j=2
         *     j=3
         *     j=4
         * 结果：3, 12, 5, 13, 15, 20, 28
         *
         * i=4 j=0 j<4
         *     j=1
         *     j=2
         *     j=3
         * 结果：3, 5, 12, 13, 15, 20, 28
         *
         * i=3 j=0 j<3
         *     j=1
         *     j=2
         * 结果：3, 5, 12, 13, 15, 20, 28
         *
         *  i=2 j=0 j<2
         *     j=1
         * 结果：3, 5, 12, 13, 15, 20, 28
         *
         *  i=1 j=0 j<1
         *     j=0
         * 结果：3, 5, 12, 13, 15, 20, 28
         */
        //12, 15, 3, 13, 5, 28, 20
        // j=0  12 15
        // j=1  12 3 15
        // j=2  12 3 13 15
        // j=3  12 3 13 5 15
        // j=4  12 3 13 5 15 28
        // j=5  12 3 13 5 15 20 28

        int n = array.length;
        //3  5 12 13 15 20 28
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            //System.out.println(i + "--->" + printResult(array));
        }

        return array;
    }

    private static String printResult(int[] array) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str = str + array[i] + " ";
        }

        return str;
    }

    /**
     * 增强
     * 注意：排序方式从后向前拍是可以增强，如果从前向后排序不能的能增强，因为最后一个可能是一个小点的数，就会出现排序不完整
     */
    private static int[] optimizeDubCre(int[] array) {
        int n = array.length;
        //不能使用增强
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            System.out.println(i + "--->" + printResult(array));
        }

//        for(int i = n -1;i > 0; i--){
//            boolean isComplete = true;
//            for(int j = 0; j < i; j++) {
//                if(array[j] > array[j+1]){
//                    int temp = array[j];
//                    array[j] = array[j+1];
//                    array[j+1] = temp;
//
//                    isComplete = false;
//                }
//            }
//
////            if(isComplete){
////               break;
////            }
//            System.out.println(i+ "--->"+printResult(array) +"---->"+isComplete);
//        }
        return array;
    }
}
