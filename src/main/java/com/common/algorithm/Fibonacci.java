package com.common.algorithm;

import java.util.stream.Stream;

/**
 * 斐波那契数列
 * f(n) = f(n-1) + f(n-2)
 * @author subo
 * @create 2018-01-26 16:17
 **/
public class Fibonacci {

    public static void main(String[] args) {
        // java8实现斐波那契数列
        System.out.println(fib(2));
    }

    // java8 实现斐波那契数列
    public static long fib(int n) {
        if (n < 0) {
            return n;
        }
        Stream.iterate(new int[]{0, 1}, t -> new int[] {t[1], t[0]+t[1]}).limit(n).map(t -> t[0]).forEach(System.out::println);
        long sum = Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(n).mapToLong(t -> t[0]).sum();
        return sum;
    }

}
