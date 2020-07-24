package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.CifData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatedRepo extends JpaRepository<CifData, Integer> {

}
