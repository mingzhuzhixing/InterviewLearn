package com.v.arithmetic;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-18 16:59 没呢
 */

public class Test1 {

    public static void main(String[] args) {
        int nums[] =new int[]{2,7,11,15};
        int target =17;

        Test1 test1=new Test1();

        int[] arr=test1.twoSum(nums, target);

        for (int i:arr){
            System.out.println(i);
        }


    }

    public int[] twoSum(int[] nums, int target) {
        int[] array=new int[2];
        for (int i=0; i < nums.length; i++){
            for (int j=i+1; j<nums.length; j++){
                if(nums[i]+ nums[j]==target){
                    array[0]=i;
                    array[1]=j;
                    break;
                }
            }
        }
        return array;
    }


}
