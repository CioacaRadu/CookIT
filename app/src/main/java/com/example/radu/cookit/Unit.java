package com.example.radu.cookit;

/**
 * Created by andreea on 18.03.2017.
 */

enum Unit {
    Mililiters("ml"), Gram("g");

    private final String unitDescription;

    private Unit(String unit)
    {
        unitDescription = unit;
    }

    public String getUnitDescription() {
        return unitDescription;
    }
}
