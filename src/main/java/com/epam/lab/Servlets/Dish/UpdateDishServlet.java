package com.epam.lab.Servlets.Dish;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.Dao.DishDao;
import com.epam.lab.Dao.implementation.DishDaoImplementation;
import com.epam.lab.Entities.Dish;
import com.mongodb.MongoClient;

public class UpdateDishServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit operation");
        }
        System.out.println("Dish edit requested with id=" + id);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        DishDao dishDao = new DishDaoImplementation(mongo);
        Dish dish = new Dish();
        dish.setId(id);
        dish = dishDao.readDish(dish);
        request.setAttribute("dish", dish);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/views/UpdateMenu.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit operation");
        }
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double price = 0d;
        try {
             price =Double.parseDouble(request.getParameter("price"));
        }catch (Exception e){
            name = null;
            description = null;
        }
        if ((name == null || name.equals("")) || (description == null || description.equals("")) ) {
            request.setAttribute("error", "Невірно введені параметри");
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            DishDaoImplementation foodDao = new DishDaoImplementation(mongo);
            List<Dish> dishes = foodDao.readAllDishes();
            request.setAttribute("dishes", dishes);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/views/menuAdmin.jsp");
            rd.forward(request, response);
        } else {
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            DishDaoImplementation foodDao = new DishDaoImplementation(mongo);
            Dish dish = new Dish();
            dish.setId(id);
            dish.setName(name);
            dish.setDescription(description);
            dish.setPrice(price);
            foodDao.updateDish(dish);
            System.out.println("Dish edited successfully with id=" + id);
            request.setAttribute("success", "Страву відредактовано успішно");
            List<Dish> dishes = foodDao.readAllDishes();
            request.setAttribute("dishes", dishes);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/views/menuAdmin.jsp");
            rd.forward(request, response);
        }
    }
}
