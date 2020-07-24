package com.cimb.vipflag.controller;

import com.cimb.vipflag.dao.CifDataChecksumRepo;
import com.cimb.vipflag.entity.CifDataChecksum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/cifchecksum")
@CrossOrigin
public class CifDataChecksumController {

    @Autowired
    public CifDataChecksumRepo cifDataChecksumRepo;

    @PostMapping("/post_data")
    public Iterable<CifDataChecksum> postToTemporaryTable (@RequestBody List<CifDataChecksum> listData){

        LocalDateTime localDateTime = LocalDateTime.now();
//        LocalDateTime date = LocalDateTime.now();
//        cifDataChecksumRepo.saveAll(listData);
        listData.forEach((data) -> {
            data.setCreatedDate(LocalDateTime.now());
            cifDataChecksumRepo.save(data);
        });
        return listData;
    }
}
