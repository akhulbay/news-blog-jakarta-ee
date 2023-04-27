package com.example.javaeefinalpoject.servlet.user;

import com.example.javaeefinalpoject.dao.UserDao;
import com.example.javaeefinalpoject.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/users")
public class UsersServlet extends HttpServlet {

    private final UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null && currentUser.getRole() == 1) {
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }
}
