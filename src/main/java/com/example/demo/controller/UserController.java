package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.dto.ServiceResponseDTO;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.log4j.Logger;

@RestController
@RequestMapping(Constant.API)
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserService userService;

    @GetMapping(Constant.USER)
    public ResponseEntity findAllUser(){
        logger.info(Constant.BEGIN_CONTROLLER + "findAllUser");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            List<User> listUser = userService.getAllUser();
            response.setData(listUser);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        } finally {
            logger.info(Constant.END_CONTROLLER + "findAllUser");
        }
    }

    @PostMapping(Constant.USER)
    public ResponseEntity createUser(@RequestBody User user){
        logger.info(Constant.BEGIN_CONTROLLER + "createUser");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            userService.createUser(user);
            response.setMessage("Created");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        } finally {
            logger.info(Constant.END_CONTROLLER + "createUser");
        }
    }

    @PutMapping(Constant.USER)
    public ResponseEntity updateUser(@RequestBody User user){
        logger.info(Constant.BEGIN_CONTROLLER + "updateUser");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            userService.updateUser(user);
            response.setMessage("Updated");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        } finally {
            logger.info(Constant.END_CONTROLLER + "updateUser");
        }
    }

    @PutMapping(Constant.USER + Constant.DISABLE +"/{id}")
    public ResponseEntity disableUser(@PathVariable int id){
        logger.info(Constant.BEGIN_CONTROLLER + "disableUser");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            userService.disableUser(id);
            response.setMessage("Disabled");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        } finally {
            logger.info(Constant.END_CONTROLLER + "disableUser");
        }
    }

    @PutMapping(Constant.USER + Constant.ENABLE +"/{id}")
    public ResponseEntity enableUser(@PathVariable int id){
        logger.info(Constant.BEGIN_CONTROLLER + "enableUser");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            userService.enableUser(id);
            response.setMessage("Enabled");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        } finally {
            logger.info(Constant.END_CONTROLLER + "enableUser");
        }
    }

    @GetMapping(Constant.USER_INFOMATION)
    public ResponseEntity getInfomation(@RequestHeader String authorization){
        logger.info(Constant.BEGIN_CONTROLLER + "getInfomation");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            User user = userService.getUserInformation(authorization);
            response.setData(user);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        } finally {
            logger.info(Constant.END_CONTROLLER + "getInfomation");
        }
    }

    @GetMapping(Constant.USER_ROLE)
    public ResponseEntity getUserRole(@RequestHeader String authorization){
        logger.info(Constant.BEGIN_CONTROLLER + "getUserRole");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            List<Role> roleList = userService.getRole(authorization);
            response.setData(roleList);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        } finally {
            logger.info(Constant.END_CONTROLLER + "getUserRole");
        }
    }
}
