package com.example.javaeefinalpoject.servlet.blockeduser;

import com.example.javaeefinalpoject.dao.BlockedUserDao;
import com.example.javaeefinalpoject.entity.BlockedUser;
import com.example.javaeefinalpoject.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/unblock-user")
public class UnblockUserServlet extends HttpServlet {

    private final BlockedUserDao blockedUserDao = BlockedUserDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null && currentUser.getRole() == 1) {
            long blockedUserId = Long.parseLong(req.getParameter("blocked-user-id"));

            boolean delete = blockedUserDao.delete(blockedUserId);
            if (delete) {
                resp.sendRedirect("/blocked-users");
            }
        } else resp.sendRedirect("/");
    }
}
