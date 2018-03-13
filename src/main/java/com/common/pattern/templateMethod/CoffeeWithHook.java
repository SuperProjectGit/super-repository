package com.common.pattern.templateMethod;

/**
 * 具体实现类  咖啡类
 *
 * @author subo
 * @create 2018-03-13 18:23
 **/
public class CoffeeWithHook extends CoffeineBerverageWithHook {
    @Override
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding sugar and milk");
    }

    @Override
    public boolean customerWantsCondiments() {
        return false;
    }
}
