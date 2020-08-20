package com.example.demo.service;

import com.example.demo.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole()throws Exception;

    public Role createRole(Role role) throws Exception;

    public Role updateRole(Role role) throws Exception;
}
