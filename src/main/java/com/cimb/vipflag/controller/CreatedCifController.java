package com.cimb.vipflag.controller;

import com.cimb.vipflag.dao.CreatedRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.entity.CifData;
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
    public List<CifData> createdData (@RequestBody List<CifData> listData, @PathVariable int userId){
        Date date = new Date();
        User findUser = userRepo.findById(userId).get();

        listData.forEach((data) -> {
            findUser.getCifData().add(data);
            data.setCreatedDate(date);
            createdRepo.save(data);
        });
        return createdRepo.findAll();
    }

    @GetMapping("/all_data")
    public List<CifData> allData (){
        return createdRepo.findAll();
    }
}
