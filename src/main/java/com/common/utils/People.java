package com.common.utils;

/**
 * @author subo
 * @create 2019-01-21 11:37
 **/
public class People {


    public static void main(String[] args) {
        Father father = new Son();
        father.hello();
        father.eat();
    }

    static class Father {
        public static void eat() {
            System.out.println("father eat");
        }
        public void hello() {
            System.out.println("father hello");
        }
    }

    static class Son extends Father{
        public static void eat() {
            System.out.println("son eat");
        }
        public void hello() {
            System.out.println("son hello");
        }
    }

}
