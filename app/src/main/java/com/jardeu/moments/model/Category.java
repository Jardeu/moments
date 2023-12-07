package com.jardeu.moments.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private int id;

    public Category() {}
    public static List<Category> categoriesList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
