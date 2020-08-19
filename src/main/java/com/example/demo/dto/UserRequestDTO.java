package com.example.demo.dto;

public class UserRequestDTO {

    private String username;
    private String password;
    private String email;
    private String fullname;
    private Boolean statusActive;

    public UserRequestDTO(String username, String password, String email, String fullname, Boolean statusActive) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.statusActive = statusActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean getStatusActive() {
        return statusActive;
    }

    public void setStatusActive(Boolean statusActive) {
        this.statusActive = statusActive;
    }
}
