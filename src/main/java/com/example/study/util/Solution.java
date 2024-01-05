package com.example.study.util;

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
}
