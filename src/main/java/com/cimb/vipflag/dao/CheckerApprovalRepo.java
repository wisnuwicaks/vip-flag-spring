package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.CheckerApproval;
import com.cimb.vipflag.entity.FileLinkDirectory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckerApprovalRepo extends JpaRepository<CheckerApproval,Integer> {
}
