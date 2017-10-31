package com.common.pattern.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Device class (adaptee in the pattern). We want to reuse this class.
 * Fishing boat moves by sailing.
 * @author subo
 * @create 2017-10-31 15:45
 **/
public class FishingBoat {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishingBoat.class);

    public void sail() {
        LOGGER.info("The fishing boat is sailing");
    }
}
