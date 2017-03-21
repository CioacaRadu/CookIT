package com.example.radu.cookit;

import java.io.Serializable;

/**
 * Created by andreea on 18.03.2017.
 */

public class Ingredient implements Serializable {

    private String name;
    private Double quantity;
    private String measuringUnit;
    private Boolean checked;

    public Ingredient(String name, Double quantity, String measuringUnit, Boolean checked) {
        this.name = name;
        this.quantity = quantity;
        this.measuringUnit = measuringUnit;
        this.checked = checked;
    }

    public Ingredient(String name) {
        this.name = name;
        this.checked = false;
    }

    public Ingredient(String name, Double quantity, String measuringUnit) {
        this.name = name;
        this.quantity = quantity;
        if(measuringUnit == null)
            this.measuringUnit = "buc";
        else
            this.measuringUnit = measuringUnit;
        this.checked = false;
    }


    public String getName() {
        return name;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String toString()
    {
        if(quantity != null) {
            Double roundQuantity = Double.valueOf(Math.round(quantity * 100) * 1.00 / 100);

            return name + ": " + roundQuantity + " " + measuringUnit;
        }
        return name;
    }
}
