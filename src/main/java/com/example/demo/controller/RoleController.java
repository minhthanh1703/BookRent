package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.dto.ServiceResponseDTO;
import com.example.demo.entities.Role;
import com.example.demo.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.API)
public class RoleController {

    private static final Logger logger = Logger.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @GetMapping(Constant.ROLE)
    public ResponseEntity findAllRole(){
        logger.info(Constant.BEGIN_CONTROLLER + "findAllRole");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            List<Role> roleList = roleService.getAllRole();
            response.setData(roleList);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "findAllRole");
        }
    }
    @PostMapping(Constant.ROLE)
    public ResponseEntity createRole(@RequestBody Role role){
        logger.info(Constant.BEGIN_CONTROLLER + "createRole");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            roleService.createRole(role);
            response.setMessage("Created");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "createRole");
        }
    }
}
