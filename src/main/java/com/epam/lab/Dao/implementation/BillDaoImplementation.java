package com.epam.lab.Dao.implementation;

import com.epam.lab.Converter.BillCoverter;
import com.epam.lab.Dao.BillDao;
import com.epam.lab.Entities.Bill;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class BillDaoImplementation implements BillDao {
    private DBCollection col;

    public BillDaoImplementation(MongoClient mongo) {
        this.col = mongo.getDB("restaurant").getCollection("bills");

    }

    public Bill createBill(Bill bill) {
        DBObject doc = BillCoverter.toDBObject(bill);
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        bill.setId(id.toString());
        return bill;
    }

    public List<Bill> readAllBills() {
        List<Bill> data = new ArrayList<Bill>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Bill bill = BillCoverter.toBill(doc);
            data.add(bill);
        }
        return data;
}

    public void deleteBill(Bill bill) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(bill.getId())).get();
        this.col.remove(query);
    }

    public Bill readBill(Bill bill) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(bill.getId())).get();
        DBObject data = this.col.findOne(query);
        return BillCoverter.toBill(data);
    }
}
