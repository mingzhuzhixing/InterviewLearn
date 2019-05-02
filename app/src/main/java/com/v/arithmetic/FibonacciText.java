package com.v.arithmetic;

/**
 * 菲波那契数列（Fibonacci）
    求Fibonacci数列：1，1，2，3，5，8，…第40个数的值。

 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-17 10:01
 */

public class FibonacciText {

    public static void main(String[] args) {

//        System.out.println("结果值："+fib(8));

        printFib(8);

    }

    /**
     * 输出菲波那契数列的第 n 个值
     *
     * 1 1         f(1) = 1
     * 2 1         f(2) = 1
     * 3 2=(1+1)   f(1) + f(2) = 2
     * 4 3=(2+1)   f(1) + f(2) + f(2) = 3
     * 5 5=(2+3)   f(1) + f(2) + f(1) + f(2) + f(2) = 5
     * 6 8=(3+5)
     * 7 13
     * 8 21
     */
    private static int fib(int n){
        if(n == 1 || n == 2){
            return 1;
        }else{
            return fib(n-2) + fib(n-1);
        }
    }


    /**
     * 打印菲波那契数列
     * 0 1
     * 1 1
     * 2 2
     * 3 3
     * 4 5
     * 5 8
     * 6 13
     *
     */
    private static void printFib(int n){
//        int f1 = 1;
//        int f2 = 1;
//        for (int i = 0; i < n; i++) {
//            if (i < 2) {
//                System.out.print("1, ");
//            } else {
//                int temp = f1;
//                f1 = f2;
//                f2 = temp + f2;
//                System.out.print(f2 + ", ");
//            }
//        }


        int[] array=new int[n];

        for (int i = 0; i < n; i++){
            if (i == 0 || i == 1){
                array[i] = 1;
            } else {
                array[i] = array[i-1] + array[i-2];
            }
        }

        for (int i:array){
            System.out.print(i+", ");
        }
    }
}
