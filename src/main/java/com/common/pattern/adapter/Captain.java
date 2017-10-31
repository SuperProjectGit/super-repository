package com.common.pattern.adapter;

/**
 * The Captain uses {@link RowingBoat} to sail.
 * This is the client in the pattern.
 * @author subo
 * @create 2017-10-31 15:51
 **/
public class Captain implements RowingBoat {

    private RowingBoat rowingBoat;

    public Captain() {
    }

    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    @Override
    public void row() {
        rowingBoat.row();
    }
}
