package com.cimb.vipflag.controller;


import com.cimb.vipflag.dao.TemporaryCifTableRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.entity.CifDataUploaded;
import com.cimb.vipflag.entity.TemporaryCifTable;
import com.cimb.vipflag.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cif_temporary")
@CrossOrigin
public class TemporaryCifTableController {

    @Autowired
    private TemporaryCifTableRepo temporaryCifTableRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/add_temporary_data/{makerId}/{fileId}/{dateString:.+}")
    public void addTemporaryCifData (@PathVariable String dateString,@PathVariable int makerId,@PathVariable int fileId, @RequestBody List<TemporaryCifTable> listData){
        LocalDateTime localDateTime =  LocalDateTime.now();
        User findUser = userRepo.findById(makerId).get();
//    cifDataUploadedRepo.saveAll(listData);
        listData.forEach(data->{

                System.out.println("masuk loop");
                data.setFileId(fileId);
                data.setMakerId(makerId);
                data.setMakerUsername(findUser.getUsername());
                data.setCreatedDate(LocalDateTime.parse(dateString));
                temporaryCifTableRepo.save(data);


        });

    }

    @PostMapping("/delete_temporary_cif/{fileId}")
    public void deleteTemporaryCif (@PathVariable int fileId){
        Iterable<TemporaryCifTable> findCifTemporary = temporaryCifTableRepo.findAllCifByFileId(fileId);
        temporaryCifTableRepo.deleteAll(findCifTemporary);

    }
}
