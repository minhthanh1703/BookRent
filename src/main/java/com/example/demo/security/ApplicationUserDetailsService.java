package com.example.demo.security;


import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.entities.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        List<UserRole> userRoles = userRoleRepository.findAllByUserId(user.getId());

        List<Role> roleList = roleRepository.findAll();

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if(user.isStatusActive() == false){
            return null;
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        userRoles.forEach((roleOfUser) ->{
            roleList.forEach((roleOfListRole)->{
                if(roleOfUser.getRoleId() == roleOfListRole.getId()){
                    authorities.add(new SimpleGrantedAuthority(roleOfListRole.getName()));
                }
            });
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
