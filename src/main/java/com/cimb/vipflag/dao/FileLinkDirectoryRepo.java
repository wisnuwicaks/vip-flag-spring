package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.FileLinkDirectory;
import com.cimb.vipflag.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FileLinkDirectoryRepo extends JpaRepository<FileLinkDirectory,Integer> {
    @Query(value = "SELECT * FROM file_link_directory ORDER BY file_id DESC LIMIT 1",nativeQuery = true)
    public Optional<FileLinkDirectory> findLastFile();

    @Query(value = "SELECT * FROM file_link_directory where checker_id=?1",nativeQuery = true)
    public Iterable<FileLinkDirectory> findFileByCheckerId(int checkerId);

    @Query(value = "SELECT * FROM file_link_directory where maker_id=?1",nativeQuery = true)
    public Iterable<FileLinkDirectory> findFileByMakerId(int makerId);

    @Query(value = "SELECT * FROM file_link_directory where maker_id=?1 and approval_status=?2",nativeQuery = true)
    public Iterable<FileLinkDirectory>  findFileByMakerStatus (int makerId,String approvalStatus);

    @Query(value = "SELECT * FROM file_link_directory where checker_id=?1 and approval_status=?2",nativeQuery = true)
    public Iterable<FileLinkDirectory>  findFileByCheckerStatus (int makerId,String approvalStatus);

    @Query(value = "SELECT * FROM file_link_directory where checker_id=?1 and approval_status=?2 or approval_status=?3 ",nativeQuery = true)
    public Iterable<FileLinkDirectory> findFileByCheckerStatus2 (int makerId,String status1, String status2);

    @Query(value ="SELECT fl.file_id,fl.created_by,fl.approved_by,fl.approval_status, fl.created_date,fl.approval_date, fl.maker_id,fl.checker_id, fl.file_name,fl.link_directory FROM file_link_directory fl inner join user_group ug on fl.maker_id=ug.maker_id where ug.checker_id=?1" , nativeQuery = true)
    public Iterable<FileLinkDirectory> findFileChecker(int checkerId);

}
