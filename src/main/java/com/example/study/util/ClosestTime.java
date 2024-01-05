package com.example.study.util;

import java.util.HashSet;
import java.util.Set;

public class ClosestTime {
    public static String findClosestTime(String timeStr) {
        // 将时间字符串拆分为小时和分钟
        String[] timeParts = timeStr.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        int hours1 = Integer.parseInt(timeParts[0]);
        int minutes1 = Integer.parseInt(timeParts[1]);

        // 获取所有可能的数字
        Set<Integer> digits = new HashSet<>();
        for (char c : timeStr.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.add(Character.getNumericValue(c));
            }
        }

        int i = 0;
        while (true) {
            i++;
            // 递减分钟数
            minutes -=i;

            if (minutes < 0) {
                // 如果分钟数小于0，则将小时数减1，并将分钟数设置为59
                hours = (hours - 1 + 24) % 24;
                minutes = 59;
            }

            // 获取当前时间的数字
            Set<Integer> currentDigits = new HashSet<>();
            currentDigits.add(hours / 10);
            currentDigits.add(hours % 10);
            currentDigits.add(minutes / 10);
            currentDigits.add(minutes % 10);

            // 检查当前时间的数字是否都在给定时间的数字中
            if (digits.containsAll(currentDigits)) {
                // 构建新的时间字符串
                return String.format("%02d:%02d", hours, minutes);
            }

            // 递加分钟数
            minutes1 += i;
            if (minutes1 >= 60) {
                // 如果分钟数小于60，则将小时数加1，并将分钟数设置为00
                hours1 = (hours1 + 1 + 24) % 24;
                minutes1 = 0;
            }
            // 获取当前时间的数字
            Set<Integer> currentDigits1 = new HashSet<>();
            currentDigits1.add(hours1 / 10);
            currentDigits1.add(hours1 % 10);
            currentDigits1.add(minutes1 / 10);
            currentDigits1.add(minutes1 % 10);
            // 检查当前时间的数字是否都在给定时间的数字中
            if (digits.containsAll(currentDigits1)) {
                // 构建新的时间字符串
                return String.format("%02d:%02d", hours1, minutes1);
            }
        }
    }

    public static String longestPalindrome(String s) {
        // start表示最长回文子串开始的位置，为了后面截取。
        // maxLen表示最长回文子串的长度
        int start = 0, maxLen = 1;
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int right = 1; right < length; right++) {
            for (int left = 0; left < right; left++) {
                // 如果两种字符不相同，肯定不能构成回文子串。
                if (s.charAt(left) != s.charAt(right))
                    continue;
                // 下面是s[left]和s[right]两个字符相同情况下的判断。
                if (right - left <= 2) {
                    // 类似于"a"，"aa"和"aba"，也是回文子串。
                    dp[left][right] = true;
                } else {
                    // 类似于"a******a"，要判断他是否是回文子串，只需要
                    // 判断"******"是否是回文子串即可。
                    dp[left][right] = dp[left + 1][right - 1];
                }
                // 如果字符串从left到right是回文子串，保存最长的回文子串。
                if (dp[left][right] && right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    start = left;
                }
            }
        }
        // 截取最长的回文子串
        return s.substring(start, start + maxLen);
    }

    public static int longestPalindromeNew(String s) {
        // start表示最长回文子串开始的位置，为了后面截取。
        // maxLen表示最长回文子串的长度
        int start = 0, maxLen = 1;
        int length = s.length();
        char[] charArray = s.toCharArray();
        // dp[i][j] 表示 s[i...j] 是否是回文子串
        boolean[][] dp = new boolean[length][length];
        // dp[i][j] = dp[i+ 1][j - 1]
        // charArray[i] = charArray[j];
        int result = 0;
        for(int i=length - 1; i >= 0; i--){
            for (int j=i; j < length; j++) {
                if((charArray[i] == charArray[j]) && (j - i <=1 || dp[i + 1][j - 1])){
                    result++;
                    dp[i][j] = true;
                    // 如果字符串从left到right是回文子串，保存最长的回文子串。
                    if(j - i + 1 > maxLen){
                        maxLen = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        // 截取最长的回文子串
        System.out.println(s.substring(start, start + maxLen));
        return  result;
    }

    public static void main(String[] args) {
//        String timeStr = "10:59";
//        String closestTimeStr = findClosestTime(timeStr);
//        System.out.println(closestTimeStr);  // 输出：19:33

//        System.out.println(longestPalindrome("babad"));
//        System.out.println(longestPalindrome("cbbd"));
//        System.out.println(longestPalindrome("a"));
//        System.out.println(longestPalindrome("ac"));
        longestPalindromeNew("cbaab");
    }
}
