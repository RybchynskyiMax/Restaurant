package com.epam.lab.Dao;

import com.epam.lab.Entities.Bill;

import java.util.List;

public interface BillDao {

    Bill createBill(Bill bill);

    List<Bill> readAllBills();

    void deleteBill(Bill bill);

    Bill readBill(Bill bill);
}
