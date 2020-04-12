package com.v.arithmetic;

/**
 * Class description here
 *
 * @author zhuming
 * @date 2019-04-18 15:46
 */

public class ExpreTest {

    public static void main(String[] args) {

        int array[] = {10, 4, 2, 7, 25, 8, 9, 6};

        int sum=0;
        int sum2=0;
        int n=array.length;
        for (int i = 0; i < n; i++){
            sum = sum+array[i];

            sum2=0;
            for (int j=i+2; j<n; j++){
                sum2=sum2+array[j];
            }

            if(sum == sum2){
                System.out.println("坐标："+(i+1));
                break;
            }
        }




//        int arrTemp[] = new int[array.length];
//
//        int sum = 0;
//        int len = array.length;
//        for (int i = 0; i < len; i++) {
//            sum = sum + array[i];
//            System.out.println(i+"---"+sum);
//            arrTemp[i] = sum;
//        }
//
//        int sum2 = 0;
//        for (int i = len - 1; i > 0; i--) {
//            sum2 = sum2 + array[i];
//
//            if(sum2  ==arrTemp[i-2] ){
//                System.out.println("坐标："+(i-1));
//                break;
//            }
//        }

    }
}
