package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.FileDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FileDirectoryRepo extends JpaRepository<FileDirectory,Integer> {
    @Query(value = "SELECT * FROM file_directory ORDER BY file_id DESC LIMIT 1",nativeQuery = true)
    public Optional<FileDirectory> findLastFile();

    @Query(value = "SELECT * FROM file_directory where checker_id=?1",nativeQuery = true)
    public Iterable<FileDirectory> findFileByCheckerId(int checkerId);

    @Query(value = "SELECT * FROM file_directory where maker_id=?1",nativeQuery = true)
    public Iterable<FileDirectory> findFileByMakerId(int makerId);

    @Query(value = "SELECT * FROM file_directory where maker_id=?1 and approval_status=?2",nativeQuery = true)
    public Iterable<FileDirectory>  findFileByMakerStatus (int makerId, String approvalStatus);

    @Query(value = "SELECT * FROM file_directory where checker_id=?1 and approval_status=?2",nativeQuery = true)
    public Iterable<FileDirectory>  findFileByCheckerStatus (int makerId, String approvalStatus);

    @Query(value = "SELECT * FROM file_directory where checker_id=?1 and approval_status=?2 or approval_status=?3 ",nativeQuery = true)
    public Iterable<FileDirectory> findFileByCheckerStatus2 (int makerId, String status1, String status2);

    @Query(value ="SELECT fl.file_id,fl.created_by,fl.approved_by,fl.approval_status, fl.created_date,fl.approval_date, fl.maker_id,fl.checker_id, fl.file_name,fl.link_directory FROM file_directory fl inner join user_group ug on fl.maker_id=ug.maker_id where ug.checker_id=?1" , nativeQuery = true)
    public Iterable<FileDirectory> findFileChecker(int checkerId);

    @Query(value ="SELECT fl.file_id,fl.created_by,fl.approved_by,fl.approval_status, fl.created_date,fl.approval_date, fl.maker_id,fl.checker_id, fl.file_name,fl.link_directory FROM file_directory fl inner join user_group ug on fl.maker_id=ug.maker_id where ug.checker_id=?1 and approval_status=?2" , nativeQuery = true)
    public Iterable<FileDirectory> findFileCheckerByStatus(int checkerId, String status);


    @Query(value ="select * from file_directory where maker_id <> ?1" , nativeQuery = true)
    public Iterable<FileDirectory> findFileCheckerNull(int checkerId);

    @Query(value ="SELECT * FROM file_directory where approval_status is not null" , nativeQuery = true)
    public Iterable<FileDirectory> findFileCheckerNotNull(int checkerId);

//    @Query(value ="SELECT fl.file_id,fl.created_by,fl.approved_by,fl.approval_status, fl.created_date,fl.approval_date, fl.maker_id,fl.checker_id, fl.file_name,fl.link_directory FROM file_directory fl inner join user_group ug on fl.maker_id=ug.maker_id where ug.checker_id=?1 and approval_status is null" , nativeQuery = true)
//    public Iterable<FileLinkDirectory> findFileCheckerNull(int checkerId);
//
//    @Query(value ="SELECT fl.file_id,fl.created_by,fl.approved_by,fl.approval_status, fl.created_date,fl.approval_date, fl.maker_id,fl.checker_id, fl.file_name,fl.link_directory FROM file_directory fl inner join user_group ug on fl.maker_id=ug.maker_id where ug.checker_id=?1 and approval_status is not null" , nativeQuery = true)
//    public Iterable<FileLinkDirectory> findFileCheckerNotNull(int checkerId);

}
