package com.epam.lab.Servlets.Bill;

import com.epam.lab.Dao.BillDao;
import com.epam.lab.Dao.implementation.BillDaoImplementation;
import com.epam.lab.Entities.Bill;
import com.mongodb.MongoClient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteBillServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for delete operation");
        }
        MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
        BillDao billDao = new BillDaoImplementation(mongo);
        Bill bill = new Bill();
        bill.setId(id);
        billDao.deleteBill(bill);
        System.out.println("Bill paid successfully with id=" + id);
        request.setAttribute("success", "Рахунок оплачено успішно");
        List<Bill> bills = billDao.readAllBills();
        request.setAttribute("bills", bills);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/views/BillsClient.jsp");
        rd.forward(request, response);
    }
}
