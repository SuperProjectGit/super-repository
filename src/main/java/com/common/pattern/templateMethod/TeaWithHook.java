package com.common.pattern.templateMethod;

/**
 * 具体实现类  茶类
 *
 * @author subo
 * @create 2018-03-13 18:25
 **/
public class TeaWithHook extends CoffeineBerverageWithHook {
    @Override
    public void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding lemon");
    }

    @Override
    public boolean customerWantsCondiments() {
        return true;
    }
}
