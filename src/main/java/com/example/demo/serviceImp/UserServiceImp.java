package com.example.demo.serviceImp;

import com.example.demo.constant.Constant;
import com.example.demo.constant.Message;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.entities.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.security.JWTVerifier;
import com.example.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImp.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public List<User> getAllUser() throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getAllUser");
        try {
            List<User> userList = new ArrayList();
            userList = userRepository.findAll();
            return userList;
        } finally {
            logger.info(Constant.END_SERVICE + "getAllUser");
        }
    }

    @Override
    public User createUser(User user) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "createUser");
        try {
            if (userRepository.findByUsername(user.getUsername()) != null) {
                throw new Exception(Message.ACCOUNT_EXIST);
            } else {
                Role role = roleRepository.getRoleByName("user");
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.setStatusActive(true);
                user.setCreateAt(new Timestamp(new Date().getTime()));
                userRepository.save(user);
                user = userRepository.findByUsername(user.getUsername());
                UserRole userRole = userRoleRepository.findByUserIdAndRoleId(user.getId(), role.getId());
                if (userRole == null) {
                    userRole = new UserRole();
                    userRole.setUserId(user.getId());
                    userRole.setRoleId(role.getId());
                    userRole.setCreateAt(new Timestamp(new Date().getTime()));
                    userRole.setStatusActive(true);
                    userRoleRepository.save(userRole);
                }
                return user;
            }
        } finally {
            logger.info(Constant.END_SERVICE + "createUser");
        }
    }


    @Override
    public User updateUser(User user) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "updateUser");
        try {
            if (userRepository.findByUsername(user.getUsername()) == null) {
                throw new Exception(Message.ACCOUNT_NOT_EXIST);
            } else {
                User userUpdate = userRepository.findByUsername(user.getUsername());
                userUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userUpdate.setFullname(user.getFullname());
                userUpdate.setEmail(user.getEmail());
                userUpdate.setUpdateAt(new Timestamp(new Date().getTime()));
                userUpdate.setStatusActive(user.isStatusActive());
                userRepository.save(userUpdate);
                return user;
            }
        } finally {
            logger.info(Constant.END_SERVICE + "updateUser");
            return null;
        }
    }

    @Override
    public User disableUser(int userId) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "disableUser");
        try {
            User user = userRepository.findById(userId);
            if (user == null) {
                throw new Exception(Message.ACCOUNT_NOT_EXIST);
            } else {
                if (user.isStatusActive()) {
                    user = changeStatus(user);
                }
                userRepository.save(user);
                return user;
            }
        } finally {
            logger.info(Constant.END_SERVICE + "disableUser");
        }
    }

    @Override
    public User enableUser(int userId) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "enableUser");
        try {
            User user = userRepository.findById(userId);
            if (user == null) {
                throw new Exception(Message.ACCOUNT_NOT_EXIST);
            } else {
                if (!user.isStatusActive()) {
                    user = changeStatus(user);
                }
                userRepository.save(user);
                return user;
            }
        } finally {
            logger.info(Constant.END_SERVICE + "enableUser");
        }
    }

    private User changeStatus(User user) {
        if (user.isStatusActive()) {
            user.setStatusActive(false);
        } else {
            user.setStatusActive(true);
        }
        return user;
    }

    @Override
    public List<Role> getRole(String token) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getRole");
        try {

        } finally {
            logger.info(Constant.END_SERVICE + "getRole");
        }
    }

    @Override
    public User getUserInformation(String token) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getUserInformation");
        try {
            User user = userRepository.findByUsername(JWTVerifier.USERNAME);
            if (user == null) {
                throw new Exception(Message.ACCOUNT_NOT_EXIST);
            } else {
                if (!user.isStatusActive()) {
                    user = changeStatus(user);
                }
                return user;
            }
        } finally {
            logger.info(Constant.END_SERVICE + "getUserInformation");
        }
    }
}
