package com.common.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author super
 * @create 2019-10-26 20:11
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 滑动窗口
 **/
public class UnRepeatString {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstringMap("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        List<Character> list = new ArrayList<>(length);
        int result = 0;
        int i = 0;
        int j = 0;
        while (i < length && j < length) {
            char temp = s.charAt(j);
            if (!list.contains(temp)) {
                list.add(temp);
                result = Math.max(result, j-i+1);
                j++;
                continue;
            }
            list.remove(Character.valueOf(s.charAt(i++)));
        }
        return result;
    }

    public static int lengthOfLongestSubstringMap(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        Map<Character, Integer> map = new HashMap(length);
        int result = 0;
        int i = 0;
        for (int j = 0; j< length; j++) {
            char temp = s.charAt(j);
            if (map.containsKey(temp)) {
                i = Math.max(i, map.get(temp) + 1);
            }
            result = Math.max(result, j - i + 1);
            map.put(temp, j);
        }
        return result;
    }
}
