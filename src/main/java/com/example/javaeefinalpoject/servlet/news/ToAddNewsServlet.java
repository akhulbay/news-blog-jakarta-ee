package com.example.javaeefinalpoject.servlet.news;

import com.example.javaeefinalpoject.dao.NewsDao;
import com.example.javaeefinalpoject.entity.News;
import com.example.javaeefinalpoject.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(value = "/add-news")
public class ToAddNewsServlet extends HttpServlet {

    private final NewsDao newsDao = NewsDao.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null) {
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            boolean save = newsDao.save(new News(null, title, content, LocalDateTime.now(), currentUser));
            if (save) {
                resp.sendRedirect("/add-news-page");
            }

        } else resp.sendRedirect("/login");
    }
}
