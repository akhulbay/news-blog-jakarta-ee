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

@WebServlet(value = "/update-news")
public class UpdateNewsServlet extends HttpServlet {
    private final NewsDao newsDao = NewsDao.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null) {
            Integer id = Integer.parseInt(req.getParameter("news-id"));
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            News news = newsDao.findById(id);
            if (news != null) {
                news.setTitle(title);
                news.setContent(content);

                boolean update = newsDao.update(news);
                if (update) {
                    resp.sendRedirect("/news-details?id=" + id);
                }
            } else resp.sendRedirect("/");
        } else resp.sendRedirect("/login");
    }
}
