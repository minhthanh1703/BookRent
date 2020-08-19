package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.security.JWTGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.API)
public class AuthController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

//    @PostMapping(Constant.AUTH + "/login")
//    public ResponseEntity loginUser(@RequestParam String username, @RequestParam String password){
////            JWTGenerator jwtGenerator = new JWTGenerator(authenticationManager())
//    }
}
