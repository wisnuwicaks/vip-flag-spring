package com.cimb.vipflag.controller;


import com.cimb.vipflag.dao.AuditTrailChangesRepo;
import com.cimb.vipflag.dao.AuditTrailLoginRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.entity.AuditTrailChanges;
import com.cimb.vipflag.entity.AuditTrailLogin;
import com.cimb.vipflag.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/audit_login")
@CrossOrigin
public class AuditTrailLoginController {

    @Autowired
    private AuditTrailLoginRepo auditTrailLoginRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/loginlog/{userId}")
    public AuditTrailLogin addUserLoginLog(@PathVariable int userId){
        LocalDateTime localDateTime = LocalDateTime.now();
        User findUser = userRepo.findById(userId).get();

        AuditTrailLogin newLog = new AuditTrailLogin();
        newLog.setLastLogin(localDateTime);
        newLog.setUserId(findUser.getUserId());
        newLog.setUsername(findUser.getUsername());

        return auditTrailLoginRepo.save(newLog);
    }

    @PostMapping("/logoutlog/{userId}")
    public AuditTrailLogin addUserLogoutLog(@PathVariable int userId){
        LocalDateTime localDateTime = LocalDateTime.now();
        AuditTrailLogin findLog = auditTrailLoginRepo.findLogByUserId(userId);
        findLog.setLastLogout(localDateTime);

        return auditTrailLoginRepo.save(findLog);
    }
}
