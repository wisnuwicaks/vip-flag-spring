package com.cimb.vipflag.controller;

import com.cimb.vipflag.dao.CreatedRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.dao.UserRoleRepo;
import com.cimb.vipflag.entity.CreatedData;
import com.cimb.vipflag.entity.User;
import com.cimb.vipflag.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private CreatedRepo createdRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    private PasswordEncoder pwEncoder = new BCryptPasswordEncoder();


    @PostMapping("/add/role/{roleName}")
    public User createdData (@RequestBody User userData, @PathVariable String roleName){
        UserRole findRole = userRoleRepo.findUserRoleByRoleName(roleName);
        userData.setUserRole(findRole);
        String encodedPassword = pwEncoder.encode(userData.getPassword());
        userData.setPassword(encodedPassword);
        return userRepo.save(userData);
    }

    @GetMapping("/all_users")
    public List<User> allUsers (){
        return userRepo.findAll();
    }

}
