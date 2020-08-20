package com.example.demo.service;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUser() throws Exception;

    public User createUser(User user) throws Exception;

    public User updateUser(User user) throws Exception;

    public User disableUser(int userId) throws Exception;

    public User enableUser(int userId) throws Exception;

    public List<Role> getRole(String token) throws Exception;

    public User getUserInformation(String token) throws Exception;
}
