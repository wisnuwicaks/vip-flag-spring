package com.cimb.vipflag.dao;


import com.cimb.vipflag.entity.AuditTrailChanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuditTrailChangesRepo extends JpaRepository<AuditTrailChanges,Integer> {
    @Query(value = "SELECT * FROM audit_trail_changes WHERE file_id= ?1", nativeQuery = true)
    public AuditTrailChanges findLogByFileId(int fileId);
}
