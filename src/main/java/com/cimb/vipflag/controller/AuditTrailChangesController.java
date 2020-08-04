package com.cimb.vipflag.controller;



import com.cimb.vipflag.dao.AuditTrailChangesRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.entity.AuditTrailChanges;
import com.cimb.vipflag.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/audit_changes")
@CrossOrigin
public class AuditTrailChangesController {

    @Autowired
    private AuditTrailChangesRepo auditTrailChangesRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/all_log")
    public Iterable<AuditTrailChanges> allChangesLog(){
        return auditTrailChangesRepo.findAll();
    }

    @PostMapping("/maker_log/{fileIdCreated}/{makerId}")
    public AuditTrailChanges addMakerLog(@PathVariable int fileIdCreated,@PathVariable int makerId){
        LocalDateTime localDateTime = LocalDateTime.now();
        User findUser = userRepo.findById(makerId).get();
        AuditTrailChanges newLog = new AuditTrailChanges();
        newLog.setFileId(fileIdCreated);
        newLog.setMakerId(makerId);
        newLog.setMakerUsername(findUser.getUsername());
        newLog.setCreatedDate(localDateTime);
        return auditTrailChangesRepo.save(newLog);
    }

    @PostMapping("/checker_log/{fileIdApproved}/{checkerId}")
    public AuditTrailChanges addChecker(@PathVariable int fileIdApproved, @PathVariable int checkerId){
        LocalDateTime localDateTime = LocalDateTime.now();
        User findUser = userRepo.findById(checkerId).get();
        AuditTrailChanges findLog = auditTrailChangesRepo.findLogByFileId(fileIdApproved);
        findLog.setCheckerId(checkerId);
        findLog.setCheckerUsername(findUser.getUsername());
        findLog.setApprovalDate(localDateTime);
        return auditTrailChangesRepo.save(findLog);
    }
}
