package com.v.java_simple;

import java.util.ArrayList;
import java.util.List;

public class JavaBasics {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(33);

        int[] array = {12, 15, 3, 13, 5, 28, 20};
        int[] arr = new int[4];

        String a = "abc";
        String b = new String("abc");
        String c = new String("abc");

        change(array[0], arr, a, b, list);

        System.out.println("--size-->" + list.size() + "===" + list.get(0));

        System.out.println(array[0]);
        System.out.println(arr[0]);
        System.out.println("===============================");

        System.out.println(b);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println("===============================");

        System.out.println(c == b);
        System.out.println(c.equals(b));

    }

    private static void change(int array, int[] arr, String str, String strB, List<Integer> list) {
        array = 9;
        arr[0] = 99;
        str = "11111";
        strB = "ABC";
        list.add(44);
    }
}
