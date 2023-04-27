package com.example.javaeefinalpoject.servlet.comment;

import com.example.javaeefinalpoject.dao.CommentDao;
import com.example.javaeefinalpoject.dao.NewsDao;
import com.example.javaeefinalpoject.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/delete-comment")
public class DeleteCommentServlet extends HttpServlet {

    private final CommentDao commentDao = CommentDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null && currentUser.getRole() == 1) {
            int commentId = Integer.parseInt(req.getParameter("comment-id"));
            int newsId = Integer.parseInt(req.getParameter("news-id"));
            boolean delete = commentDao.delete(commentId);
            if (delete) {
                resp.sendRedirect("/news-details?id=" + newsId);
            }
        } else resp.sendRedirect("/");
    }
}
