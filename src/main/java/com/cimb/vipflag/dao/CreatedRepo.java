package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.CreatedData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatedRepo extends JpaRepository<CreatedData, Integer> {

}
