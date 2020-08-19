package com.example.demo.repository;

import com.example.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAll();

    Role getRoleByName(String roleName);
}
