package com.epam.lab.Servlets.Dish;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.epam.lab.Dao.DishDao;
import com.epam.lab.Dao.implementation.DishDaoImplementation;
import com.epam.lab.Entities.Dish;
import com.mongodb.MongoClient;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
public class AddDishServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        System.out.println(name);
        String description = request.getParameter("description");
        System.out.println(description);
        Double price = 0d;
        try {
            price =Double.parseDouble(request.getParameter("price"));
        }catch (Exception e){
            name = null;
            description = null;
        }
        if ((name == null || name.equals("")) || (description == null || description.equals(""))) {
            request.setAttribute("error", "Невірно введені параметри");
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            DishDao dishDao = new DishDaoImplementation(mongo);
            List<Dish> dishes = dishDao.readAllDishes();
            request.setAttribute("dishes", dishes);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/views/menuAdmin.jsp");
            rd.forward(request, response);
        } else {
            Dish dish = new Dish();
            dish.setDescription(description);
            dish.setName(name);
            dish.setPrice(price);
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            DishDao dishDao = new DishDaoImplementation(mongo);
            dishDao.createDish(dish);
            System.out.println("Dish Added Successfully with id="+ dish.getId());
            request.setAttribute("success", "Страву додано успішно");
            List<Dish> dishes = dishDao.readAllDishes();
            request.setAttribute("dishes", dishes);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/views/menuAdmin.jsp");
            rd.forward(request, response);
        }
    }
}
