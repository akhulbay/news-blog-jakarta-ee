package com.example.javaeefinalpoject.entity;

import java.time.LocalDateTime;

public class News {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime postDate;
    private User user;

    public News() {
    }

    public News(Integer id, String title, String content, LocalDateTime postDate, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
