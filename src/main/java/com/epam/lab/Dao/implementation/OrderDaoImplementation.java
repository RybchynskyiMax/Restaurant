package com.epam.lab.Dao.implementation;

import com.epam.lab.Converter.OrderConverter;
import com.epam.lab.Dao.OrderDao;
import com.epam.lab.Entities.Order;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImplementation implements OrderDao {
    private DBCollection col;
    private MongoClient mongoClient;
    public OrderDaoImplementation(MongoClient mongo) {
        this.col = mongo.getDB("restaurant").getCollection("orders");
        this.mongoClient=mongo;
    }
    public Order createOrder(Order order) {
        DBObject doc = OrderConverter.toDBObject(order,mongoClient);
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        order.setId(id.toString());
        return order;
    }

    public void updateOrder(Order order) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(order.getId())).get();
        this.col.update(query,OrderConverter.toDBObject(order,mongoClient));
    }

    public List<Order> readAllOrders() {
        List<Order> data = new ArrayList<Order>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Order order = OrderConverter.toOrder(doc,mongoClient);
            data.add(order);
        }
        return data;
    }

    public void deleteOrder(Order order) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(order.getId())).get();
        this.col.remove(query);
    }

    public Order readOrder(Order order) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(order.getId())).get();
        DBObject data = this.col.findOne(query);
        return OrderConverter.toOrder(data,mongoClient);
    }

    public List<Order> confirmedOrders() {
        List<Order> data = new ArrayList<Order>();
        DBObject query = BasicDBObjectBuilder.start().append("confirmed", true).get();
        DBCursor cursor = col.find(query);
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Order order = OrderConverter.toOrder(doc,mongoClient);
            data.add(order);
        }
        return data;
    }

    public List<Order> unConfirmedOrders() {
        List<Order> data = new ArrayList<Order>();
        DBObject query = BasicDBObjectBuilder.start().append("confirmed", false).get();
        DBCursor cursor = col.find(query);
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Order order = OrderConverter.toOrder(doc,mongoClient);
            data.add(order);
        }
        return data;
    }

    public List<Order> findOrdersByDishId(String id) {
        List<Order> data = new ArrayList<Order>();
        ObjectId objectId = new ObjectId(id);
        DBObject query = BasicDBObjectBuilder.start().append("dishes", objectId).get();
        DBCursor cursor = col.find(query);
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Order order = OrderConverter.toOrder(doc,mongoClient);
            data.add(order);
        }
        return data;
    }


}
