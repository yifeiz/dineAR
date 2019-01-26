package com.kingapps.dinear;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private ArrayList<String> dishes;
    private ArrayList<String> imageURLs;

    public Restaurant(String name, ArrayList<String> dishes, ArrayList<String> imageURLs) {
        this.name = name;
        this.dishes = dishes;
        this.imageURLs = imageURLs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<String> dishes) {
        this.dishes = dishes;
    }

    public ArrayList<String> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(ArrayList<String> imageURLs) {
        this.imageURLs = imageURLs;
    }
}
