package com.example.javaeefinalpoject.servlet.news;

import com.example.javaeefinalpoject.dao.CommentDao;
import com.example.javaeefinalpoject.dao.NewsDao;
import com.example.javaeefinalpoject.entity.Comment;
import com.example.javaeefinalpoject.entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {

    private final NewsDao newsDao = NewsDao.getInstance();
    private final CommentDao commentDao = CommentDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        News news = newsDao.findById(id);
        List<Comment> comments = commentDao.findAllByNewsId(id);

        req.setAttribute("comments", comments);
        req.setAttribute("news", news);
        req.getRequestDispatcher("/newsdetails.jsp").forward(req, resp);
    }
}
