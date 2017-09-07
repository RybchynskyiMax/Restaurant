package com.epam.lab.Converter;

import com.epam.lab.Entities.Dish;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

public class DishConverter {
    public static DBObject toDBObject(Dish dish){
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
        builder.append("name", dish.getName());
        builder.append("description", dish.getDescription());
        builder.append("price", dish.getPrice());
        if(dish.getId()!=null){
            builder.append("_id",new ObjectId(dish.getId()));
        }
        return builder.get();
    }

    public static Dish toDish(DBObject doc){
        Dish dish = new Dish();
        dish.setName((String) doc.get("name"));
        dish.setDescription((String) doc.get("description"));
        dish.setPrice((Double) doc.get("price"));
        ObjectId id =(ObjectId) doc.get("_id");
        dish.setId(id.toString());
        return dish;
    }
}
