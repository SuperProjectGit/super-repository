package com.common.pattern.state;

import java.util.Random;

/**
 * 投入硬币
 * @author super
 * @create 2018-01-08 22:14
 **/
public class HasCoin implements State {

    private CandyMachine candyMachine;

    public HasCoin(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("you can't insert another coin!");
    }

    @Override
    public void returnCoin() {
        System.out.println("coin return!");
        candyMachine.setState(candyMachine.mOnReadyState);
    }

    @Override
    public void turnCrank() {
        System.out.println("crank turn...!");
        Random ranwinner=new Random();
        int winner=ranwinner.nextInt(10);
        if(winner==0) {
            candyMachine.setState(candyMachine.mWinnerState);
        } else {
            candyMachine.setState(candyMachine.mSoldState);
        }
    }

    @Override
    public void dispense() {

    }

    @Override
    public void printstate() {
        System.out.println("***HasCoin***");
    }
}
