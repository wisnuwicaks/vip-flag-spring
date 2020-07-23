package com.cimb.vipflag.entity;


import javax.persistence.*;
import java.sql.Date;


@Entity
public class FileLinkDirectory  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileId;

    private String linkDirectory;
    private Date createdDate;
    private Date approvalDate;
    private Date approvalStatus;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
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

    public Date getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Date approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
