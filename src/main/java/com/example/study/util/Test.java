package com.example.study.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        // 给定以“HH:MM”格式表示的时间，通过重用当前数字形成最接近的时间。
        //一个数字可以重复使用的次数没有限制。
        //您可以假设给定的输入字符串始终有效。 例如，“01:34”、“12:09”均有效。
        //
        //示例1：
        //输入：“19:34”
        //输出：“19:33”
        //解释：从数字 1、9、3、4 中选择最接近的时间是 19:33

        List<String> list = new ArrayList<>();
        String str = "19:34";
        String[] strArr = str.split(":");
        String tar = strArr[1];
        System.out.println(str.length());
        for (int i = 4; i >= 0 ;i--){
            if(i != 2) {
                list.add(String.valueOf(str.charAt(i)));
            }
        }
        System.out.println(list);

        int result = 0;
        int  r = 1;
        for (int t = 1; t >= 0 ;t--){
            int temp = Integer.valueOf(String.valueOf(tar.charAt(t))) * r;
            r = r * 60;
            result += temp;
        }
        // 10:59
        // 11:00
        System.out.println(result);
        // 枚举 所有组合2位分秒最接近result的值且分秒组合小于60，
        // list.forEach();
        // 然后比较差值，取最小值


    }
}
