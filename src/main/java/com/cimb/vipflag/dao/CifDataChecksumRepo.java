package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.CifDataChecksum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CifDataChecksumRepo extends JpaRepository<CifDataChecksum, Integer> {
}
