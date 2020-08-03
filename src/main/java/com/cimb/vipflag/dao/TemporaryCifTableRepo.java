package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.FileDirectory;
import com.cimb.vipflag.entity.TemporaryCifTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TemporaryCifTableRepo extends JpaRepository<TemporaryCifTable,Integer> {
    @Query(value = "SELECT * FROM temporary_cif_table where file_id =?1",nativeQuery = true)
    public Iterable<TemporaryCifTable> findAllCifByFileId(int fileId);
}
