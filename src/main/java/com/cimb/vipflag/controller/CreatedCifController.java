package com.cimb.vipflag.controller;

import com.cimb.vipflag.dao.CreatedRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.entity.CreatedData;
import com.cimb.vipflag.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/created")
@CrossOrigin
public class CreatedCifController {
    @Autowired
    private CreatedRepo createdRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/create_data/{userId}")
    public List<CreatedData> createdData (@RequestBody List<CreatedData> listData, @PathVariable int userId){
        Date date = new Date();
        User findUser = userRepo.findById(userId).get();

        listData.forEach((data) -> {
            findUser.getCreatedData().add(data);
            data.setCreatedDate(date);
            createdRepo.save(data);
        });
        return createdRepo.findAll();
    }

    @GetMapping("/all_data")
    public List<CreatedData> allData (){
        return createdRepo.findAll();
    }
}
