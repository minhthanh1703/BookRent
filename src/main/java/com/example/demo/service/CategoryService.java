package com.example.demo.service;

import com.example.demo.entities.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategory() throws Exception;

    public Category createCategory(Category category) throws Exception;

    public Category updateCategory(Category category) throws Exception;

    public Category disableCategory(int categoryId) throws Exception;

    public Category enableCategory(int categoryId) throws Exception;
}
