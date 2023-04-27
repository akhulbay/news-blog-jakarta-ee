package com.example.javaeefinalpoject.servlet.auth;

import com.example.javaeefinalpoject.dao.BlockedUserDao;
import com.example.javaeefinalpoject.dao.UserDao;
import com.example.javaeefinalpoject.entity.BlockedUser;
import com.example.javaeefinalpoject.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();
    private final BlockedUserDao blockedUserDao = BlockedUserDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String redirect = "/login?fail";
        User user = userDao.findByEmail(email);
        BlockedUser blockedUser = blockedUserDao.findByUserId(user.getId());
        if (user != null) {
            redirect = "/login?blockeduser";
            if (blockedUser == null) {
                if (user.getPassword().equals(password)) {
                    redirect = "/home";
                    req.getSession().setAttribute("CURRENT_USER", user);
                }
            }
        }
        resp.sendRedirect(redirect);
    }
}