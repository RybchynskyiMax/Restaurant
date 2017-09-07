package com.epam.lab.Servlets.Menu;

import com.epam.lab.Dao.DishDao;
import com.epam.lab.Dao.implementation.DishDaoImplementation;
import com.epam.lab.Entities.Dish;
import com.mongodb.MongoClient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MenuAdminServlet extends HttpServlet {
    private static final long serialVersionUID = -7060758261496829905L;
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
        DishDao dishDao = new DishDaoImplementation(mongo);
        List<Dish> dishes = dishDao.readAllDishes();
        request.setAttribute("dishes", dishes);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/views/menuAdmin.jsp");
        rd.forward(request, response);
    }
}
