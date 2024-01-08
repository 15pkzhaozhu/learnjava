package com.example.study.util;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int maxlen = 1;
        int len = s.length();
        char[] charArray = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (charArray[i] == charArray[j] && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i + 1 > maxlen) {
                        maxlen = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxlen);
    }

//    题意
//    给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t 。
//
//    样例
//    例 1:
//    输入: “eceba”
//    输出: 3
//    解释: t 是 “ece”，长度为3。
//
//    例 2:
//    输入: “ccaabbb”
//    输出: 5
//    解释: t 是 “aabbb”，长度为5。

    public static void long2Str(String s) {
        int len =  s.length();
        Map<Character,Integer> map = new HashMap<>();
        int left = 0;
        int max = 1;
        int start = 0;
        int maxlen = 0;
        for( int i= 0; i< len; i++){
            char c = s.charAt(i);
            if(map.keySet().size()== 2 && !map.keySet().contains(c)){
                int tempmin = map.values().stream().min((a,b)->a-b).get();
                left =Math.max(left,tempmin+1);
            }
            map.put(c,i);
            max = Math.max(max,i-left+1);
            if(max > maxlen){
                maxlen = max;
                start = left;
            }
        }
        System.out.println(s.substring(start,start + maxlen));
    }

    public static void main(String[] args) {
        long2Str("eceba");
        long2Str("ccaabbb");
        long2Str("aaaaaaa");
        long2Str("");
    }
}
