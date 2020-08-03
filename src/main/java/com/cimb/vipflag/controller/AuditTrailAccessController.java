package com.cimb.vipflag.controller;


import com.cimb.vipflag.dao.AuditTrailAccessRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.entity.AuditTrailAccess;
import com.cimb.vipflag.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/audit_access")
@CrossOrigin
public class AuditTrailAccessController {
    @Autowired
    private AuditTrailAccessRepo auditTrailAccessRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/accesslog")
    public AuditTrailAccess addUserLog(@RequestBody AuditTrailAccess userLog){
        LocalDateTime localDateTime = LocalDateTime.now();
        User findUser = userRepo.findById(userLog.getUserId()).get();
        AuditTrailAccess newLog = new AuditTrailAccess();
        newLog.setUserId(userLog.getUserId());
        newLog.setUsername(findUser.getUsername());
        newLog.setActionDate(localDateTime);
        newLog.setActionDescription(userLog.getActionDescription());


        return auditTrailAccessRepo.save(newLog);
    }
}
