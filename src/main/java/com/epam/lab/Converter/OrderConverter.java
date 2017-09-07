package com.epam.lab.Converter;

import com.epam.lab.Dao.DishDao;
import com.epam.lab.Dao.implementation.DishDaoImplementation;
import com.epam.lab.Entities.Dish;
import com.epam.lab.Entities.Order;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class OrderConverter {
    public static DBObject toDBObject(Order order, MongoClient mongoClient){
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
        ArrayList<ObjectId> dishes = new ArrayList<ObjectId>();
        for (Dish dish : order.getDishes()) {
        dishes.add(new ObjectId(dish.getId()));
        }
        builder.append("dishes",dishes);
        builder.append("money",order.getMoney());
        builder.append("confirmed",order.isConfirmed());
        if(order.getId()!=null){
            builder.append("_id",new ObjectId(order.getId()));
        }
        return builder.get();
    }


    public static Order toOrder(DBObject doc, MongoClient mongoClient){
        DishDao dishDao = new DishDaoImplementation(mongoClient);
        Order order = new Order();
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        BasicDBList dbList = (BasicDBList) doc.get("dishes");
        for (Object aDbList : dbList) {
            dishes.add(dishDao.findById(aDbList.toString()));
        }
        order.setMoney((Double)doc.get("money"));
        order.setDishes(dishes);
        order.setConfirmed((Boolean) doc.get("confirmed"));
        ObjectId id =(ObjectId) doc.get("_id");
        order.setId(id.toString());
        return order;
    }
}
