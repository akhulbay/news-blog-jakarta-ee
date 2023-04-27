package com.example.javaeefinalpoject.entity;

public class BlockedUser {
    private Long id;
    private String reason;
    private User user;

    public BlockedUser() {
    }

    public BlockedUser(Long id, String reason, User user) {
        this.id = id;
        this.reason = reason;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

