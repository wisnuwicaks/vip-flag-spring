package com.cimb.vipflag.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class FileLinkDirectory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileId;

    private String linkDirectory;
    private Date dateCreated;
    private Date dateApproved;

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }


}
