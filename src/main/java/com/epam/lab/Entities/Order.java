package com.epam.lab.Entities;
import java.util.ArrayList;

public class Order {
private String id;
private ArrayList<Dish> dishes;
private double money;
private boolean confirmed;
    public Order(String id, ArrayList<Dish> dishes, Double money, Boolean confirmed) {
        this.id = id;
        this.dishes = dishes;
        this.money = money;
        this.confirmed = confirmed;
    }

    public Order() {
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    public double  getMoney() {
        return money;
    }

    public void setMoney(Double  money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
