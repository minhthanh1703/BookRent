package com.example.demo.repository;

import com.example.demo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();

    Category getByName(String name);

    Category findById(int id);
}
