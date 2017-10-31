package com.common.pattern.adapter;

/**
 * Program entry point.
 *
 * @author subo
 * @create 2017-10-31 15:59
 **/
public class App {

    public static void main(String[] args) {
        Captain captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }
}
