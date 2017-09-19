package com.common.support;

import java.util.ArrayList;
import java.util.List;

/**
 * Java8新特性
 *
 * @author subo
 * @create 2017-09-07 18:10
 **/
public class Features {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.stream().filter(s->list.contains("3")).forEach(s -> System.out.println(s));

    }
}
