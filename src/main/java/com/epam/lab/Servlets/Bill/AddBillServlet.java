package com.epam.lab.Servlets.Bill;

import com.epam.lab.Dao.BillDao;
import com.epam.lab.Dao.OrderDao;
import com.epam.lab.Dao.implementation.BillDaoImplementation;
import com.epam.lab.Dao.implementation.OrderDaoImplementation;
import com.epam.lab.Entities.Bill;
import com.epam.lab.Entities.Dish;
import com.epam.lab.Entities.Order;
import com.mongodb.MongoClient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddBillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("id");
        if (order==null || "".equals(order)) {
            request.setAttribute("error", "Mandatory Parameters Missing");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/views/OrdersAdmin.jsp");
            rd.forward(request, response);
        } else {
            Bill bill = new Bill();
            Order orderObject = new Order();
            orderObject.setId(order);
            String description=" ";
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            OrderDao orderDao = new OrderDaoImplementation(mongo);
            BillDao billDao = new BillDaoImplementation(mongo);
            orderObject = orderDao.readOrder(orderObject);
            for (Dish dish : orderObject.getDishes()) {
                description+=" "+ dish.getName();
            }
            bill.setMoney(orderObject.getMoney());
            bill.setDescription(description);
            billDao.createBill(bill);
            System.out.println("Bill Added Successfully with id="+bill.getId());
            request.setAttribute("success", "Рахунок додано успішно");
            List<Order> confirmedOrders = orderDao.confirmedOrders();
            List<Order> unConfirmedOrders = orderDao.unConfirmedOrders();
            request.setAttribute("confirmedOrders", confirmedOrders);
            request.setAttribute("notConfirmedOrders", unConfirmedOrders);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/views/OrdersAdmin.jsp");
            rd.forward(request, response);
        }
    }
}
