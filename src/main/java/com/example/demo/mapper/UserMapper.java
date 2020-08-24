package com.example.demo.mapper;

import com.example.demo.entities.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper  {

    List<Role> getRoleByUser(Map parameter);

}
