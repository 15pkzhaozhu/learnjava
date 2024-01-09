package com.example.study.zz;

import java.util.Scanner;

public class Main {
    public static int getBot(int a,int res) {
        // 商
        int d = a / 3;
        res += d;
        // 余数
        int m = a % 3;
        if (d + m < 2) {
            return res;
        } else if (d + m == 2) {
            res += 1;
            return res;
        }
        return getBot(d + m,res);
    }

    /**
     * 汽水瓶
     * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
     * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
     * 数据范围：输入的正整数满足 1≤�≤100 1≤n≤100 
     *
     * 注意：本题存在多组输入。输入的 0 表示输入结束，并不用输出结果。
     * @param args
     */
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            int a = in.nextInt();
//            int res = getBot(a,0);
//            if(res>0){
//                System.out.println(res);
//            }
//        }
//    }

    /**
     * 明明的随机数
     * 明明生成了�N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
     * 数据范围： 1≤�≤1000 1≤n≤1000  ，输入的数字大小满足 1≤���≤500 1≤val≤500 
     * @param args
     */
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        Set<Integer> num_set =  new HashSet<>();
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//        int n = in.nextInt();
//        for(int i = 0; i<n;i++){
//            int val = in.nextInt();
//            if(!minHeap.contains(val)){
//                minHeap.offer(val);
//            }
//        }
//        while(!minHeap.isEmpty()){
//            System.out.println(minHeap.poll());
//        }
//    }

    /**
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示
     * @param a
     * @return
     */
    public static String getoxValue(String a){
        String res = "";
        String sub = a.substring(2);
        int len = sub.length();
        int sum = 0;
        int r = 1;
        for(int i = len-1;i>=0;i--){
            char c = sub.charAt(i);
            int t = 0;
            if(!Character.isDigit(c)){
                int asciiCode = (int)c;
                t = asciiCode -55;
            } else {
                t = Character.getNumericValue(c);
            }
            sum += t * r;
            r= r*16;
        }
        return String.valueOf(sum);

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String a = in.next();
            System.out.println(getoxValue(a));
        }
    }
}