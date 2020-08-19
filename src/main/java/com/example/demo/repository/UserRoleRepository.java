package com.example.demo.repository;

import com.example.demo.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findAll();

    List<UserRole> findAllByUserId(int userId);

    UserRole findByUserIdAndRoleId(int userId, int roleId);
}
