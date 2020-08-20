package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.dto.ServiceResponseDTO;
import com.example.demo.entities.Category;
import com.example.demo.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Constant.API)
public class CategoryController {
    private static final Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping(Constant.CATEGORY)
    public ResponseEntity findAllCategory(){
        logger.info(Constant.BEGIN_CONTROLLER + "findAllCategory");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            List<Category> categoryList = categoryService.getAllCategory();
            response.setData(categoryList);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "findAllCategory");
        }
    }

    @PostMapping(Constant.CATEGORY)
    public ResponseEntity createCategory(@RequestBody Category category){
        logger.info(Constant.BEGIN_CONTROLLER + "createCategory");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            categoryService.createCategory(category);
            response.setMessage("Created");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "createCategory");
        }
    }

    @PutMapping(Constant.CATEGORY)
    public ResponseEntity updateCategory(@RequestBody Category category){
        logger.info(Constant.BEGIN_CONTROLLER + "updateCategory");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            categoryService.updateCategory(category);
            response.setMessage("Updated");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "updateCategory");
        }
    }

}
