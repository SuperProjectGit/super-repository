package com.common.pattern.state;
/**
 * 准备状态
 * @author super
 * @create 2018-01-08 22:18
 **/
public class OnReadyState implements State {

    private CandyMachine candyMachine;

    public OnReadyState(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("you have inserted a coin,next,please turn crank!");
        candyMachine.setState(candyMachine.mHasCoin);
    }

    @Override
    public void returnCoin() {
        System.out.println("you haven't inserted a coin yet!");
    }

    @Override
    public void turnCrank() {
        System.out.println("you turned,but you haven't inserted a coin!");
    }

    @Override
    public void dispense() {

    }

    @Override
    public void printstate() {
        System.out.println("***OnReadyState***");
    }
}
