package com.example.javaeefinalpoject.servlet.comment;

import com.example.javaeefinalpoject.dao.CommentDao;
import com.example.javaeefinalpoject.dao.NewsDao;
import com.example.javaeefinalpoject.entity.Comment;
import com.example.javaeefinalpoject.entity.News;
import com.example.javaeefinalpoject.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(value = "/add-comment")
public class AddCommentServlet extends HttpServlet {

    private final NewsDao newsDao = NewsDao.getInstance();
    private final CommentDao commentDao = CommentDao.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null) {
            Integer newsId = Integer.parseInt(req.getParameter("news-id"));
            String comment = req.getParameter("comment");

            News news = newsDao.findById(newsId);
            if (news != null) {
                boolean save = commentDao.save(new Comment(null, comment, LocalDateTime.now(), currentUser, news));
                if (save) {
                    resp.sendRedirect("/news-details?id=" + news.getId());
                } else resp.sendRedirect("/");
            } else resp.sendRedirect("/");
        } else resp.sendRedirect("/login");
    }
}
