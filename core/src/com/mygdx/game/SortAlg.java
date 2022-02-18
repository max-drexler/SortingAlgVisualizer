package com.mygdx.game;

import java.util.Arrays;

public enum SortAlg {
    BUBBLE,
    MERGE,
    SELECTION,
    INSERTION,
    QUICK;

    private SortAlg() {
    }
    public static String[] names() {
        return Arrays.toString(SortAlg.values()).replaceAll("^.|.$", "").split(", ");
    }
}
