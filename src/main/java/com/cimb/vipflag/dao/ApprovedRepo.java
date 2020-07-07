package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.ApprovedData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovedRepo extends JpaRepository<ApprovedData, Integer> {
}
