package com.common.pattern.templateMethod;

/**
 * 模板方法模式是类的行为模式。准备一个抽象类，将部分逻辑以具体方法以及具体构造函数的形式实现，然后声明一些抽象方法来迫使子类实现剩余的逻辑。
 * 不同的子类可以以不同的方式实现这些抽象方法，从而对剩余的逻辑有不同的实现。这就是模板方法模式的用意。
 * 比如定义一个操作中的算法的骨架，将步骤延迟到子类中。模板方法使得子类能够不去改变一个算法的结构即可重定义算法的某些特定步骤。
 * 抽象类（AbstractClass）：实现了模板方法，定义了算法的骨架。
 * 具体类（ConcreteClass)：实现抽象类中的抽象方法，已完成完整的算法。
 *
 * 抽象类
 *
 * @author subo
 * @create 2018-03-13 17:57
 **/
public abstract class CoffeineBerverageWithHook {

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    public abstract void brew();

    public abstract void addCondiments();

    public void boilWater() {
        System.out.println("Boilling water");
    }

    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    public boolean customerWantsCondiments() {
        return true;
    }
}
