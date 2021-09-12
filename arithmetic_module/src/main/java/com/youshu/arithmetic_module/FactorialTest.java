package com.youshu.arithmetic_module;

/**
 * 阶乘
 * 随便给一参数n，n不小于0，假如n=3，则算出1 * 2 * 3 = 6，如n=5，则算出1 * 2 * 3 * 4 * 5 = ?
 *
 * @author zhuming
 * @date 2019-04-17 10:24
 */

public class FactorialTest {

    public static void main(String[] args) {
        System.out.println("阶乘结果："+fac(5));
    }


    /**
     * 阶乘
     * 1 1        f(1)==1
     * 2 1*2      f(2)== f(1)*2
     * 3 1*2*3    f(3)== f(2)*3
     * 4 1*2*3*4  f(4)== f(3)*4
     * 5 1*2*3*4*5
     */
    private static int fac(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n* fac(n-1);
        }
    }
}


