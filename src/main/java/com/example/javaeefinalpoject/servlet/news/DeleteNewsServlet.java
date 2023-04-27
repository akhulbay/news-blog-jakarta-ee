package com.example.javaeefinalpoject.servlet.news;

import com.example.javaeefinalpoject.dao.NewsDao;
import com.example.javaeefinalpoject.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/delete-news")
public class DeleteNewsServlet extends HttpServlet {

    private final NewsDao newsDao = NewsDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null && currentUser.getRole() == 1) {
            int newsId = Integer.parseInt(req.getParameter("news-id"));
            boolean delete = newsDao.delete(newsId);
            if (delete) {
                resp.sendRedirect("/news-page");
            }
        } else resp.sendRedirect("/");
    }
}
