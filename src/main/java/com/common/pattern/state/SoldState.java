package com.common.pattern.state;

/**
 * 售出状态
 *
 * @author super
 * @create 2018-01-08 22:22
 **/
public class SoldState implements State {

    private CandyMachine candyMachine;

    public SoldState(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("please wait!we are giving you a candy!");
    }

    @Override
    public void returnCoin() {
        System.out.println("you haven't inserted a coin yet!");
    }

    @Override
    public void turnCrank() {
        System.out.println("we are giving you a candy,turning another get nothing,!");
    }

    @Override
    public void dispense() {
        candyMachine.releaseCandy();
        if (candyMachine.getCount() > 0) {
            candyMachine.setState(candyMachine.mOnReadyState);
        } else {
            System.out.println("Oo,out of candies");
            candyMachine.setState(candyMachine.mSoldOutState);
        }
    }

    @Override
    public void printstate() {
        System.out.println("***SoldState***");
    }
}
