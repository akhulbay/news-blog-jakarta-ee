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

@WebServlet(value = "/block-user")
public class BlockUserServlet extends HttpServlet {

    private final UserDao userDao = UserDao.getInstance();
    private final BlockedUserDao blockedUserDao = BlockedUserDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null && currentUser.getRole() == 1) {
            long userId = Long.parseLong(req.getParameter("user-id"));
            String reason = req.getParameter("reason");

            User user = userDao.findById(userId);
            boolean save = blockedUserDao.save(new BlockedUser(null, reason, user));
            if (save) {
                resp.sendRedirect("/users");
            }
        } else resp.sendRedirect("/");
    }
}
