package com.v.arithmetic;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-19 18:39
 */

public class Test2 {


    public static void main(String[] args) {
//        method1();
//       int  a = (int)("1");
       String b="1323";
       int b1= Integer.parseInt(b);
       int b2= Integer.valueOf(b).intValue();
//       int b3= (Integer) b;

//       double d=(double)b3;
        System.out.println(2.0-1.1);

//        System.out.println(MAX_VALUE);

    }


    /**
     * 1 一个整数，大于0，不用循环和本地变量，按照n,2n,4n,8n的顺序递增，当值大于5000时，把值按照指定顺序输出来。
     * <p>
     * 例：n=1237
     * <p>
     * 则输出为：1237,2474,4948,9896,9896,4948,2474,1237
     */
    public static void method1() {
        result = 1237;
        resultIndex = 0;
        printNumber(result, resultIndex);
        System.out.println(result + "---" + resultIndex);
        printNumber2(result, resultIndex);

    }

    private static int result;
    private static int resultIndex;

    private static void printNumber(int num, int index) {

        if (index == 0) {
            index = 1;
        } else {
            index = index * 2;
        }
        result = num * index;
        System.out.print(result + ",");
        if (result < 5000) {
            printNumber(num, index);
        }
        resultIndex = index;
    }

    private static void printNumber2(int num, int index) {
        System.out.println(num + "---" + index);
        int result = num / index;
        index = index / 2;
        System.out.print(result + ",");
        if (index >= 1) {
            printNumber(num, index);
        }
    }
}
