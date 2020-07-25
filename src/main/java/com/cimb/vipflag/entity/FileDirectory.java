package com.cimb.vipflag.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class FileDirectory {
    @Id
    private int fileId;

    private String linkDirectory;
    private String fileName;
    private String createdBy;
    private String approvedBy;
    private LocalDateTime createdDate;
    private LocalDateTime approvalDate;
    private String approvalStatus;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "maker_id")
    private User userMaker;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "checker_id")
    private User userChecker;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getLinkDirectory() {
        return linkDirectory;
    }

    public void setLinkDirectory(String linkDirectory) {
        this.linkDirectory = linkDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public User getUserMaker() {
        return userMaker;
    }

    public void setUserMaker(User userMaker) {
        this.userMaker = userMaker;
    }

    public User getUserChecker() {
        return userChecker;
    }

    public void setUserChecker(User userChecker) {
        this.userChecker = userChecker;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
