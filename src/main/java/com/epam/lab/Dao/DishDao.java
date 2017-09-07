package com.epam.lab.Dao;

import com.epam.lab.Entities.Dish;

import java.util.List;

public interface DishDao {

    Dish createDish(Dish dish);

    void updateDish(Dish dish);

    List<Dish> readAllDishes();

    void deleteDish(Dish dish);

    Dish readDish(Dish dish);

    Dish findById(String id);
}
