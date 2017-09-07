package com.epam.lab.Converter;

import com.epam.lab.Entities.Bill;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

public class BillCoverter {
    public static DBObject toDBObject(Bill bill){
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
        builder.append("money",bill.getMoney());
        builder.append("description",bill.getDescription());
        if(bill.getId()!=null){
            builder.append("_id",new ObjectId(bill.getId()));
        }
        return builder.get();
    }

    public static Bill toBill(DBObject dbObject){
        Bill bill = new Bill();
        bill.setMoney((Double)dbObject.get("money"));
        bill.setDescription((String) dbObject.get("description"));
        ObjectId id =(ObjectId) dbObject.get("_id");
        bill.setId(id.toString());
        return bill;
    }
}
