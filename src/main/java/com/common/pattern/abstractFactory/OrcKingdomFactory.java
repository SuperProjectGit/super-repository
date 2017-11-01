package com.common.pattern.abstractFactory;

/**
 * OrcKingdomfactory concrete factory
 *
 * @author subo
 * @create 2017-11-01 15:52
 **/
public class OrcKingdomFactory implements KingdomFactory {
    @Override
    public King createKing() {
        return new OrcKing();
    }

    @Override
    public Castle createCastle() {
        return new OrcCastle();
    }

    @Override
    public Army createArmy() {
        return new OrcArmy();
    }
}
