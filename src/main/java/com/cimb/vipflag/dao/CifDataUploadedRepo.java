package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.CifDataChecksum;
import com.cimb.vipflag.entity.CifDataUploaded;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CifDataUploadedRepo extends JpaRepository<CifDataUploaded, Integer> {

}
