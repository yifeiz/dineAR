package com.kingapps.dinear;

public class Dish {
    private String name;
    private String arCode;

    public Dish(String name, String arCode, int imageResource) {
        this.name = name;
        this.arCode = arCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArCode() {
        return arCode;
    }

    public void setArCode(String arCode) {
        this.arCode = arCode;
    }
}
