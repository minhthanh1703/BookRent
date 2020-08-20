package com.example.demo.serviceImp;

import com.example.demo.constant.Constant;
import com.example.demo.constant.Message;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public Role createRole(Role role) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "createRole");
        try{
            if(roleRepository.getRoleByName(role.getName()) != null){
                throw new Exception(Message.ROLE_EXIST);
            }else {
                role.setName(role.getName());
                role.setCreateAt(new Timestamp(new Date().getTime()));
                role.setStatusActive(true);
                roleRepository.save(role);
                return role;
            }
        }finally {
            logger.info(Constant.END_SERVICE + "createRole");
        }
    }

    @Override
    public Role updateRole(Role role) throws Exception {
        return null;
    }


}
