package com.example.javaeefinalpoject.servlet.blockeduser;

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
import java.util.List;

@WebServlet(value = "/blocked-users")
public class BlockedUsersServlet extends HttpServlet {

    private final UserDao userDao = UserDao.getInstance();
    private final BlockedUserDao blockedUserDao = BlockedUserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null && currentUser.getRole() == 1) {
            List<BlockedUser> users = blockedUserDao.findAll();
            req.setAttribute("blockedusers", users);
            req.getRequestDispatcher("/blockedusers.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }
}
