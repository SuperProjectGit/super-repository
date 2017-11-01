package com.common.pattern.abstractFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * app
 *
 * @author subo
 * @create 2017-11-01 15:54
 **/
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private King king;

    private Castle castle;

    private Army army;

    /**
     * create kingdom
     * @param factory
     */
    public void createKingdom(KingdomFactory factory) {
        setArmy(factory.createArmy());
        setKing(factory.createKing());
        setCastle(factory.createCastle());
    }

    public King getKing(KingdomFactory factory) {
        return factory.createKing();
    }

    public King getKing() {
        return king;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public Castle getCastle(KingdomFactory factory) {
        return factory.createCastle();
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public Army getArmy(KingdomFactory factory) {
        return factory.createArmy();
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public static void main(String[] args) {
        App app = new App();

        LOGGER.info("Elven Kingdom");
        app.createKingdom(new ElfKingdomFactory());
        LOGGER.info(app.getKing().getDescription());
        LOGGER.info(app.getArmy().getDescription());
        LOGGER.info(app.getCastle().getDescription());

        LOGGER.info("Orc Kingdom");
        app.createKingdom(new OrcKingdomFactory());
        LOGGER.info(app.getKing().getDescription());
        LOGGER.info(app.getArmy().getDescription());
        LOGGER.info(app.getCastle().getDescription());
    }
}
