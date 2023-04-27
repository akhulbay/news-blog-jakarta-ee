package com.example.javaeefinalpoject.entity;

public class User {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String birthDate;
    private Integer role;

    public User() {
    }

    public User(Long id, String fullName, String email, String password, String birthDate, Integer role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
