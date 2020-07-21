package com.cimb.vipflag.controller;

import com.cimb.vipflag.dao.CreatedRepo;
import com.cimb.vipflag.entity.CreatedData;
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

    @PostMapping("/create_data")
    public List<CreatedData> createdData (@RequestBody List<CreatedData> listData){
        Date date = new Date();
        listData.forEach((data) -> {
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
