package com.common.algorithm;

import java.util.Arrays;
import java.util.Optional;

/**
 * statis array
 * 给定数组A，大小为n，数组元素为1到n的数字，不过有的数字出现了多次，有的数字没有出现。
 * 请给出算法和程序，统计哪些数字没有出现，哪些数字出现了多少次。
 * 能够在O(n)的时间复杂度，O(1)的空间复杂度要求下完成么？
 * 分析：如果对时间复杂度没有要求，那么先排序再统计就ok，时间O(n*lgn)，空间O(1)。
 * 如果对空间没有要求，那么使用位图或者哈希表，时间O(1)，空间O(n)。
 * @author subo
 * @create 2017-11-13 11:25
 **/
public class StatisArray {

    /**
     * 通过对原数组进行改造，使得数字k出现在以他为下标的位置上。
     * 如数组54131，经过改造后变成11345，然后再统计出现次数，为了与原数组中的数字区分开，使用负数统计，
     * 即0代表未出现过，-j代表出现j次。
     * @param A
     */
    public static void statis(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        int n = A.length;
        for (int i= 0; i< n; i++) {
            if (A[i] > 0) {// 当前位置尚未统计过
                // 保存当前位置并置为0，说明i尚未出现
                int t = A[i];
                A[i] = 0;
                while (A[t] > 0) {// 尚未统计t出现的次数
                    int temp = A[t];
                    A[t] = -1;//t出现一次
                    t = temp;// 循环统计原来A[t]值在数组中出现的次数
                }
                A[t] --;// 已统计过，-1即可
            }
        }

        for (int i=1; i< n; i++) {
            System.out.println(i + " occurs " + Math.abs(A[i]) + " times");
        }
    }

    /**
     * 顺序扫描数组，当a[i]>0即a[i]尚未被统计过时,根据a[i]与i的大小分类讨论。
     * 1、a[i] = i：说明i出现在正确位置上，且是第一次出现，直接将a[i]置为-1，代表出现过一次；
     * 2、a[i] < i：说明a[i]出现在他应该出现的位置后面，“小数在后”，由于是顺序扫描，他应该出现的那个位置肯定已经被统计过，直接-1即可，即a[a[i]] -= 1;
     * 3、a[i] > i：说明a[i]出现在他应该出现的位置前面，“大数在前”，此时不能直接像2中那样修改，因为，大数应该出现的位置可能没有统计过，此时要再次分类讨论：
     *　　3.1、当a[a[i]] == a[i]，即a[i]出现了两次，此时可以直接将a[a[i]] = -2;因为在当前位置之前，a[i]这个数肯定未出现过，不然已经被当做情况3讨论过，a[a[i]]应该就是负数了(代表a[i]出现的次数)；
     *　　3.2、当a[a[i]] < 0，即a[i]已经被考察过了，直接-1，即a[a[i]] -= 1;
     *　　3.3、其余情况指，a[a[i]]是另一个尚未统计过的数，此时将a[i]换到正确的位置a[a[i]]，继续考察a[i]。
     * @param a
     */
    public static void statisA(int[] a) {
        Optional<int[]> op =  Optional.ofNullable(a);
        if (!op.isPresent()) {
            return;
        }
        int n = a.length;
        for (int i= 0; i< n; i++) {
            if (a[i] <= 0) {
                continue;
            }
            boolean succ = false;
            while (!succ) {
                if (a[i] == i) {
                    a[i] = -1;
                    succ = true;
                } else if (a[i] < i) {
                    a[a[i]] -= 1;
                    a[i] = 0;
                    succ = true;
                } else if (a[i] > i) {
                    if(a[a[i]] == a[i]) {
                        a[a[i]] = -2;
                        a[i] = 0;
                        succ = true;
                    } else if (a[a[i]] < 0) {
                        a[a[i]] -= 1;
                        a[i] = 0;
                        succ = true;
                    } else {
                        int temp = a[a[i]];
                        int t = a[i];
                        a[t] = t;
                        a[i] = temp;
                    }
                }
            }
        }

        for (int i=1; i< n; i++) {
            System.out.println(i + " occurs " + Math.abs(a[i]) + " times");
        }
    }

    /**
     * 步骤一：a[i]=a[i]*(n+1)
     * 步骤二：a[a[i]/(n+1)]++
     * 步骤三：输出a[i]%(n+1)即为则依次为i在数组出现的次数
     * @param a
     */
    public static void statisB(int[] a) {
        Optional<int[]> op = Optional.ofNullable(a);
        if (!op.isPresent()) {
            return;
        }
        int n = a.length;
        for (int i= 0; i< n; i++) {
            a[i]=a[i]*(n+1);
        }
        for (int i= 0; i< n; i++) {
            a[a[i]/(n+1)]++;
        }

        for (int i= 1; i< n; i++) {
            System.out.println(i + " occurs " + a[i]%(n+1) + " times");
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 4, 1, 1, 3, 2};
        //statis(A);
        //statisA(A);
        statisB(A);
    }
}
