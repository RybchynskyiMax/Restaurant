package com.epam.lab.Dao.implementation;

import com.epam.lab.Converter.DishConverter;
import com.epam.lab.Dao.DishDao;
import com.epam.lab.Entities.Dish;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class DishDaoImplementation implements DishDao {
    private DBCollection col;

    public DishDaoImplementation(MongoClient mongo) {
        this.col = mongo.getDB("restaurant").getCollection("dishes");

    }

    public Dish createDish(Dish dish) {
        DBObject doc = DishConverter.toDBObject(dish);
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        dish.setId(id.toString());
        return dish;
    }

    public void updateDish(Dish dish) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(dish.getId())).get();
        this.col.update(query, DishConverter.toDBObject(dish));
    }

    public List<Dish> readAllDishes() {
        List<Dish> data = new ArrayList<Dish>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Dish dish = DishConverter.toDish(doc);
            data.add(dish);
        }
        return data;
    }

    public void deleteDish(Dish dish) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(dish.getId())).get();
        this.col.remove(query);
    }

    public Dish readDish(Dish dish) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(dish.getId())).get();
        DBObject data = this.col.findOne(query);
        return DishConverter.toDish(data);
    }

    public Dish findById(String id) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(id)).get();
        DBObject data = this.col.findOne(query);
        return DishConverter.toDish(data);
    }


}
