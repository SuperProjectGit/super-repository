package com.common.algorithm;

import java.util.Random;

/**
 * 给一个函数，返回 0 和 1，概率为 p 和 1-p，请你实现一个函数，使得返回 01 概率一样。
 * 调用两次给定函数，01和10出现的概率均为p*(1-p),利用这个概率相等返回同概率0和1
 * @author subo
 * @create 2018-01-29 10:15
 **/
public class Randm01 {

    public static int equally() {
        Random random = new Random();
        while (true) {
            int i = random.nextInt(2);
            int j = random.nextInt(2);
            if (i == 0 && j == 1) {
                return 0;
            } else if ((i == 1 && j == 0)) {
                return 1;
            } else {
                continue;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(equally());
        System.out.println(1 | 1);
        System.out.println(0 | 0);
        System.out.println(0 | 1);
    }

}
