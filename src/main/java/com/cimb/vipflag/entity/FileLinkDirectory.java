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

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
