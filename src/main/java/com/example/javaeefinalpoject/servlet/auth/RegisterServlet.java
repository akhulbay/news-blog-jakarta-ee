package com.example.javaeefinalpoject.servlet.auth;

import com.example.javaeefinalpoject.dao.UserDao;
import com.example.javaeefinalpoject.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    private final UserDao userDao = UserDao.getInstance();
    private static final Integer ROLE = 2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re-password");
        String birthDate = req.getParameter("birth-date");

        String redirect = "/register?emailerror";
        User user = userDao.findByEmail(email);
        if (user == null) {
            redirect = "/register?passworderror";
            if (password.equals(rePassword)) {
                boolean save = userDao.save(new User(null, firstName + " " + lastName, email, password, birthDate, ROLE));
                if (save) redirect = "/login";
            }
        }
        resp.sendRedirect(redirect);

    }
}
