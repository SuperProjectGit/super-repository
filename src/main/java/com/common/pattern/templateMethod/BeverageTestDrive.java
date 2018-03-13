package com.common.pattern.templateMethod;

/**
 * 测试类
 *
 * @author subo
 * @create 2018-03-13 18:29
 **/
public class BeverageTestDrive {

    public static void main(String[] args) {
        TeaWithHook teaWithHook = new TeaWithHook();
        CoffeeWithHook coffeeWithHook = new CoffeeWithHook();
        System.out.println("Making tea...");
        teaWithHook.prepareRecipe();
        System.out.println("Making coffee...");
        coffeeWithHook.prepareRecipe();
    }
}
