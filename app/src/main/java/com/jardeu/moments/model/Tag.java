package com.jardeu.moments.model;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private int id;
    private String name;

    public Tag() {}
    public static List<Tag> tagsList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
