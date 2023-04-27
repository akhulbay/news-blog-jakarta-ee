package com.example.javaeefinalpoject.servlet.news;

import com.example.javaeefinalpoject.dao.NewsDao;
import com.example.javaeefinalpoject.entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/news-page")
public class NewsPageServlet extends HttpServlet {
    private final NewsDao newsDao = NewsDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        if (key != null) {
            List<News> newsList = newsDao.search(key);
            req.setAttribute("newsList", newsList);
        } else {
            List<News> newsList = newsDao.findAll();
            req.setAttribute("newsList", newsList);
        }
        req.getRequestDispatcher("/news.jsp").forward(req, resp);
    }
}
