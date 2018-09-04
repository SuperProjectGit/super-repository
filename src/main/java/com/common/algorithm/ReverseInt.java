package com.common.algorithm;

/**
 * 逆序整数输出
 *
 * @author subo
 * @create 2018-09-03 17:19
 **/
public class ReverseInt {

    public Integer reverse(Integer num) {
        if (null == num || num.equals(0)) {
            return num;
        }
        Integer result = 0;
        while (!num.equals(0)) {
            Integer temp = num % 10;
            result = result*10 + temp;
            num /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInt().reverse(3421));
    }
}
