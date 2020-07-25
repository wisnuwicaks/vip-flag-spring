package com.cimb.vipflag.controller;

import com.cimb.vipflag.dao.UserGroupRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.entity.User;
import com.cimb.vipflag.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usergroup")
@CrossOrigin

public class UserGroupController {

    @Autowired
    private UserGroupRepo userGroupRepo;

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/all")
    public Iterable<UserGroup> getAllRelation(){
        return userGroupRepo.findAll();
    }
    @GetMapping("/get_maker_relation/{makerId}")
    public Iterable<UserGroup> getMakerRelation(@PathVariable int makerId){
        return userGroupRepo.findMakerRelation(makerId);
    }




    @PostMapping("/add_relation/{makerId}/{checkerId}")
    public UserGroup addUserRelation(@PathVariable int makerId, @PathVariable int checkerId){
        User findMaker = userRepo.findById(makerId).get();
        User findChecker = userRepo.findById(checkerId).get();

        UserGroup newRelation = new UserGroup();
        newRelation.setMakerId(findMaker.getUserId());
        newRelation.setChecker(findChecker);
        return userGroupRepo.save(newRelation);


    }
}