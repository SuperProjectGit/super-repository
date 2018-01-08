package com.common.pattern.state;
/**
 * 状态接口
 * @author super
 * @create 2018-01-08 22:10
 **/
public interface State {

    public void insertCoin();
    public void returnCoin();
    public void turnCrank();
    public void dispense();
    public void printstate();

}
