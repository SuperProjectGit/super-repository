package com.common.pattern.abstractFactory;

/**
 * kingdom factory interface
 *
 * @author subo
 * @create 2017-11-01 15:24
 **/
public interface KingdomFactory {

    King createKing();

    Castle createCastle();

    Army createArmy();

}
