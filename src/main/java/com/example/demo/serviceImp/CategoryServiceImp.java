package com.example.demo.serviceImp;

import com.example.demo.constant.Constant;
import com.example.demo.constant.Message;
import com.example.demo.entities.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    private static final Logger logger = Logger.getLogger(CategoryServiceImp.class);

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategory() throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getAllCategory");
        try{
            List<Category> categoryList = new ArrayList<>();
            categoryList = categoryRepository.findAll();
            return categoryList;
        }finally {
            logger.info(Constant.END_SERVICE + "getAllCategory");
        }
    }

    @Override
    public Category createCategory(Category category) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "createCategory");
        try{
            if(categoryRepository.getByName(category.getName()) != null){
                throw new Exception(Message.CATEGORY_EXIST);
            }else{
                category.setName(category.getName());
                category.setCreateAt(new Timestamp(new Date().getTime()));
                category.setStatusActive(true);

                categoryRepository.save(category);
                return category;
            }
        }finally {
            logger.info(Constant.END_SERVICE + "createCategory");
        }
    }

    @Override
    public Category updateCategory(Category category) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "updateCategory");
        try{
            if(categoryRepository.findById(category.getId()) == null){
                throw new Exception(Message.CATEGORY_NOT_EXIST);
            }else {
                Category categoryUpdate = categoryRepository.findById(category.getId());
                categoryUpdate.setName(category.getName());
                categoryUpdate.setUpdateAt(new Timestamp(new Date().getTime()));
                categoryUpdate.setStatusActive(category.isStatusActive());

                categoryRepository.save(category);
                return category;
            }
        }finally {
            logger.info(Constant.END_SERVICE + "updateCategory");
            return null;
        }
    }

    @Override
    public Category disableCategory(int categoryId) throws Exception {
        return null;
    }

    @Override
    public Category enableCategory(int categoryId) throws Exception {
        return null;
    }
}
