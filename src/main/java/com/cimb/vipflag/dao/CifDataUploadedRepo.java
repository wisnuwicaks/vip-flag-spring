package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.CifDataUploaded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CifDataUploadedRepo extends JpaRepository<CifDataUploaded, Integer> {

    @Query(value = "select * from cif_data_uploaded where cfcifn=?1",nativeQuery = true)
    public CifDataUploaded findCFCIFN(long cfcifn );
}
