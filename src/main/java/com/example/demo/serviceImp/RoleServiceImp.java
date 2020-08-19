package com.example.demo.serviceImp;

import com.example.demo.constant.Constant;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private static final Logger logger = Logger.getLogger(RoleServiceImp.class);

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getAllRole");
        try {
            List<Role> roleList = new ArrayList();
            roleList = roleRepository.findAll();
            return roleList;
        }finally {
            logger.info(Constant.END_SERVICE + "getAllRole");
        }
    }




}
