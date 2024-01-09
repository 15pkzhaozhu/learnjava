package com.example.study.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
            minutes -= i;

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
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                if ((charArray[i] == charArray[j]) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    result++;
                    dp[i][j] = true;
                    // 如果字符串从left到right是回文子串，保存最长的回文子串。
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        // 截取最长的回文子串
        System.out.println(s.substring(start, start + maxLen));
        return result;
    }

//    给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
//    示例 1：
//    输入：s = "aacecaaa"
//    输出："aaacecaaa"
//    示例 2：
//    输入：s = "abcd"
//    输出："dcbabcd"

    public static String shorPalindrome(String s) {
        char[] arr = s.toCharArray();
        int len = s.length();
        Set<Character> set = new HashSet<Character>();
        for (char c : arr) {
            if (!set.contains(c)) {
                set.add(c);
            }
        }
        if (set.size() == 1) {
            return s;
        } else {
            boolean[][] dpold = new boolean[len][len];
            // 查找s从0开始的最长回文串
            int max0Len = 1;
            for (int i = len - 1; i >= 0; i--) {
                for (int j = i; j < len; j++) {
                    if (arr[i] == arr[j] && (j - i <= 1 || dpold[i + 1][j - 1])) {
                        dpold[i][j] = true;
                        if (i == 0 && j - i + 1 > max0Len) {
                            max0Len = j - i + 1;
                        }
                    }
                }
            }

            String deals = s;
            int count = 0;
            for (char c : arr) {
                if (count >= max0Len) {
                    deals = c + deals;
                }
                count++;
            }
            return deals;
        }
    }

    public static String largestTimeFromDigits(int[] arr) {
        //arr[0] 0、1、2
        //arr[1] 0-9，0-9，0-3
        //arr[2] 0-5
        //arr[3] 0-9
        int hour;
        int min;
        // if(!Arrays.stream(arr).anyMatch(i-> i == 0)
        //         && !Arrays.stream(arr).anyMatch(i-> i == 1)
        //         && !Arrays.stream(arr).anyMatch(i-> i == 2)) {
        //     return "";
        // }
        int flag = 0;
        for (int i = 0; i < 4; i++) {
            if (arr[i] != 0 && arr[i] != 1 && arr[i] != 2) {
                flag += 1;
            }
        }
        if (flag == 4) {
            return "";
        }
        String res = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j != i) {
                    for (int k = 0; k < 4; k++) {
                        if (k != j && k != i) {
                            hour = arr[i] * 10 + arr[j];
                            min = arr[k] * 10 + arr[6 - i - j - k];
                            if (hour < 24 && min < 60) {
                                String t = String.format("%02d:%02d", hour, min);
                                if (t.compareTo(res) > 0) {
                                    res = t;
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public int[] canSeePersonsCount(int[] heights) {
        // i < j 且 min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1])
        int[] res = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            int t = 0;
            if (i == heights.length - 1) {
                t = 0;
            } else {
                for (int j = i + 1; j < heights.length; j++) {
                    if (j - i == 1) {
                        t += 1;
                    } else {
                        int min = heights[i] < heights[j] ? heights[i] : heights[j];
                        int max = heights[i + 1];
                        for (int k = i + 1; k < j; k++) {
                            if (heights[k] > max) {
                                max = heights[k];
                            }
                        }
                        if (min > max) {
                            t += 1;
                        }
                    }
                }
            }
            res[i] = t;
        }
        return res;
    }

    public static int[] canSeePersonsCount1(int[] heights) {
//        peek() ：返回栈顶元素，不在堆栈中删除它。
//        pop() ：返回栈顶元素，并在进程中删除它。
//        push()：在栈顶增加元素
        //element() ：返回栈底元素，不在堆栈中删除它。
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int[] res = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int h = heights[i];
            while (!stack.isEmpty() && stack.peek() < h) {
                stack.pop();
                res[i]++;
            }
            if (!stack.isEmpty()) {
                res[i]++;
            }
            stack.push(h);
        }
        return res;
    }

    public static String removeDuplicateLetters(String s) {
        Deque<Character> stack = new ArrayDeque<Character>();
        char[] charArr = s.toCharArray();
        int len = charArr.length;
        for (int i = 0; i < len; i++) {
            char c = charArr[i];
            // 剩余后面的字符创
            String leftStr = s.substring(i + 1);
            while (!stack.isEmpty()
                    && stack.peek().compareTo(c) > 0
                    && leftStr.contains(String.valueOf(stack.peek()))
                    && !stack.contains(c)) {
                stack.pop();
            }
            if (!stack.contains(c)) {
                stack.push(c);
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.peek() + res;
            stack.pop();
        }
        return res;
    }

    public String rankTeams(String[] votes) {
        if(votes.length == 0){
            return "";
        }
        int size = votes[0].length();
        Map<Character,Integer> map = new HashMap<>(size);
        for(String s : votes){
            char[] charArr = s.toCharArray();
            int temp = 1;
            for(int i = size -1 ; i >= 0; i--){
                if(map.containsKey(charArr[i])){
                   int value = map.get(charArr[i]).intValue() + temp;
                   map.put(charArr[i],value);
                }else{
                    map.put(charArr[i],temp);
                }
                temp = temp * 10;
            }
        }



        //利用Map的entrySet方法，转化为list进行排序
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());
        //利用Collections的sort方法对list排序
        Collections.sort(entryList, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                // 倒序反过来
                return o2.getValue() - o1.getValue() !=0 ?o2.getValue() - o1.getValue()
                        : o1.getKey().compareTo(o2.getKey());
            }
        });
        //遍历排序好的list，一定要放进LinkedHashMap，因为只有LinkedHashMap是根据插入顺序进行存储
        LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character,Integer> e : entryList
        ) {
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character,Integer> e : linkedHashMap.entrySet()){
            sb.append(e.getKey());
        }
        return sb.toString();
    }

    public static Map<Character, Integer> sortMap(Map<Character, Integer> map) {
        //利用Map的entrySet方法，转化为list进行排序
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());
        //利用Collections的sort方法对list排序
        Collections.sort(entryList, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                // 倒序反过来
                return o2.getValue() - o1.getValue() !=0 ?o2.getValue() - o1.getValue()
                        : o1.getKey().compareTo(o2.getKey());
            }
        });
        //遍历排序好的list，一定要放进LinkedHashMap，因为只有LinkedHashMap是根据插入顺序进行存储
        LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character,Integer> e : entryList
        ) {
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        return linkedHashMap;
    }

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int sumProfit = 0;
        int dlen = difficulty.length;
        for(int w: worker){
            int matchDindex = -1;
            int cha = -1;
            for(int dindex = 0 ; dindex < dlen; dindex ++){
                if(w >= difficulty[dindex]){
                    if(cha==-1){
                        cha = w-difficulty[dindex];
                        matchDindex = dindex;
                    } else{
                        if(cha > w-difficulty[dindex]){
                            matchDindex = dindex;
                            cha = w-difficulty[dindex];
                        }
                    }
                }
            }
            if(matchDindex > 0){
                sumProfit += profit[matchDindex];
            }
        }
        return sumProfit;
    }

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if(len == 0){
            return 0;
        }
        char[] charArr = s.toCharArray();
        int res = 1;
        for(int i = 0; i< len; i++){
            char c  = charArr[i];
            String str = String.valueOf(c);
            for(int j = i+1; j< len;j++){
                if(str.contains(String.valueOf(charArr[j]))){
                    break;
                } else {
                    str = str + String.valueOf(charArr[j]);
                }
            }
            res = Math.max(str.length(),res);
        }
        return res;
    }

    public static int lengthOfLongestSubstring1(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;

    }

    public static void main(String[] args) {
//        String timeStr = "10:59";
//        String closestTimeStr = findClosestTime(timeStr);
//        System.out.println(closestTimeStr);  // 输出：19:33

//        System.out.println(longestPalindrome("babad"));
//        System.out.println(longestPalindrome("cbbd"));
//        System.out.println(longestPalindrome("a"));
//        System.out.println(longestPalindrome("ac"));
        // longestPalindromeNew("cbaab");
        // System.out.println(shorPalindrome("aabaabaafa"));
//        System.out.println(largestTimeFromDigits(new int[]{1, 2, 3, 4}));
//        System.out.println(largestTimeFromDigits(new int[]{5, 5, 5, 5}));
//        System.out.println(largestTimeFromDigits(new int[]{0, 0, 0, 0}));

        // System.out.println(canSeePersonsCount1(new int[]{10,6,8,5,11,9}));
        // System.out.println(canSeePersonsCount1(new int[]{1, 2, 3, 4, 5, 6}));
        // System.out.println(canSeePersonsCount1(new int[]{6, 5, 4, 3, 2, 1}));

       //  System.out.println(maxProfitAssignment(new int[]{13,37,58},new int[]{4,90,96},  new int[]{34,73,45} ));

        System.out.println(lengthOfLongestSubstring1("pwwkew"));
    }
}
