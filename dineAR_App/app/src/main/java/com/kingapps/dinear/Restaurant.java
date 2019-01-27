package com.kingapps.dinear;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private ArrayList<Dish> dishes;
    private int imageURL;

    public Restaurant(String name, ArrayList<Dish> dishes, int imageURL) {
        this.name = name;
        this.dishes = dishes;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    public int getImageURLs() {
        return imageURL;
    }

    public void setImageURLs(int imageURLs) {
        this.imageURL = imageURLs;
    }
}
