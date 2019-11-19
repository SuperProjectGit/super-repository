package com.common.algorithm;

/**
 * @author super
 * @create 2019-11-12 22:01
 **/
public class StringReverse {

    public static void main(String[] args) {
        String str = "we are    one  champion";
        System.out.println(reverse(str));
    }

    public static String reverse(String str) {
        if (null == str || str.length() == 0) {
            return str;
        }
        char[] chars = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            char t = chars[length-i-1];
            if (Character.isWhitespace(t)) {
                if (temp.length() > 0) {
                    builder.append(temp.reverse());
                    temp.delete(0, temp.length());
                }
                builder.append(t);
                continue;
            }
            temp.append(t);
        }
        if (temp.length() > 0) {
            builder.append(temp.reverse());
        }
        return builder.toString();
    }
}
