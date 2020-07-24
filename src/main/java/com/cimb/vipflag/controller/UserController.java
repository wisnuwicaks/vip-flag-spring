package com.cimb.vipflag.controller;

import com.cimb.vipflag.dao.CreatedRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.dao.UserRoleRepo;
import com.cimb.vipflag.entity.User;
import com.cimb.vipflag.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    @GetMapping("/{userId}")
    public User getUserById (@PathVariable int userId){
        User findUser = userRepo.findById(userId).get();
        return findUser;
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername (@PathVariable String username){
        User findUser = userRepo.findByUsername(username);
        System.out.println(findUser);
        return findUser;
    }

    @PostMapping("/add/role/{roleName}")
    public User addUserWithRole (@RequestBody User userData, @PathVariable String roleName){
        UserRole findRole = userRoleRepo.findUserRoleByRoleName(roleName);
        userData.setUserRole(findRole);
//        String encodedPassword = pwEncoder.encode(userData.getPassword());
//        userData.setPassword(encodedPassword);
        return userRepo.save(userData);
    }

    @PostMapping("/{userId}/role/{roleName}")
    public User addUserRole (@PathVariable int userId,@PathVariable String roleName){
        UserRole findRole = userRoleRepo.findUserRoleByRoleName(roleName);
        User findUser = userRepo.findById(userId).get();
        findUser.setUserRole(findRole);
        return userRepo.save(findUser);
    }

    @PostMapping("/{userId}/role/{roleName}")
    public User removeUserRole (@PathVariable int userId,@PathVariable String roleName){
        UserRole findRole = userRoleRepo.findUserRoleByRoleName(roleName);
        User findUser = userRepo.findById(userId).get();

        return userRepo.save(findUser);
    }

    @GetMapping("/all_users")
    public List<User> allUsers (){
        return userRepo.findAll();
    }

    @GetMapping("/login")
    public User getLoginParams(@RequestParam String username, @RequestParam String password){
        User findUser = userRepo.findUserLogin(username,password);
        return findUser;
    }

    @PostMapping("/register")
    @Transactional
    public User registerUser(@RequestBody User user) {
//        String encodedPassword = pwEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

        userRepo.save(user);

//        String linkToVerify = "http://localhost:8080/users/verify/" + user.getUsername() + "?token=" + verifyToken;
//        String hrefhtml ="<a href=\""+linkToVerify+"\">link</a>";
//
//        String message = "<h1>Welcome to New Style!</h1>\n Your account has been successfully registered!\n Click this "+hrefhtml+ " to verify your email \n";
//        message+="if link doesnt work, click link bellow : \n";
//        message+= "\n <a href=\""+linkToVerify+"\">"+linkToVerify+"</a>";

//        emailUtil.sendEmail(user.getEmail(), "Email Confirmation", message);
        return user;
    }


}
