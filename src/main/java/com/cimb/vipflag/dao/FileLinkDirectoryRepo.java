package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.FileLinkDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FileLinkDirectoryRepo extends JpaRepository<FileLinkDirectory,Integer> {
    @Query(value = "SELECT * FROM file_link_directory ORDER BY file_id DESC LIMIT 1",nativeQuery = true)
    public Optional<FileLinkDirectory> findLastFile();
}
