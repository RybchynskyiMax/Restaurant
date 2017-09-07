package com.epam.lab.Entities;

public class Dish {
    private String Id;
    private String name;
    private String description;
    private double price;

    public Dish(String id, String name, String description, double price) {
        Id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Dish() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
