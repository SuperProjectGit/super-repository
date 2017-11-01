package com.common.pattern.abstractFactory;

/**
 * ElfKingdomFactory concrete factory
 *
 * @author subo
 * @create 2017-11-01 15:47
 **/
public class ElfKingdomFactory implements KingdomFactory {
    @Override
    public King createKing() {
        return new ElfKing();
    }

    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }

    @Override
    public Army createArmy() {
        return new ElfArmy();
    }
}
