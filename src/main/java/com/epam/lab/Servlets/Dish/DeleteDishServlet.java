package com.epam.lab.Servlets.Dish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.Dao.DishDao;
import com.epam.lab.Dao.OrderDao;
import com.epam.lab.Dao.implementation.DishDaoImplementation;
import com.epam.lab.Dao.implementation.OrderDaoImplementation;
import com.epam.lab.Entities.Dish;
import com.epam.lab.Entities.Order;
import com.mongodb.MongoClient;
public class DeleteDishServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for delete operation");
        }
        MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
        DishDao dishDao = new DishDaoImplementation(mongo);
        OrderDao orderDao = new OrderDaoImplementation(mongo);
        Dish food = new Dish();
        food.setId(id);
        List<Order> ordersWithThisDish = orderDao.findOrdersByDishId(id);
        for (Order withThisDish : ordersWithThisDish) {
            double money=0;
            ArrayList<Dish> dishes =withThisDish.getDishes();
            Iterator<Dish> foodIterator = dishes.iterator();
            while (foodIterator.hasNext()){
                if (foodIterator.next().getId().equals(id)){
                    foodIterator.remove();
                }
            }
            for (Dish dish : dishes) {
                money+=dish.getPrice();
            }
            withThisDish.setMoney(money);
            withThisDish.setDishes(dishes);
            orderDao.updateOrder(withThisDish);
        }
        dishDao.deleteDish(food);
        System.out.println("Dish deleted successfully with id=" + id);
        request.setAttribute("success", "Страву видалено успішно");
        List<Dish> dishes = dishDao.readAllDishes();
        request.setAttribute("dishes", dishes);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/views/menuAdmin.jsp");
        rd.forward(request, response);
    }
}
