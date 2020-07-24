package com.cimb.vipflag.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CifDataChecksum {
    @Id
    private int cfcifn;

    private String cfvipi;
    private String cfvipc;
    private  Date createdDate;
    private  Date approvalDate;
    private String approvalStatus;

    public int getCfcifn() {
        return cfcifn;
    }

    public void setCfcifn(int cfcifn) {
        this.cfcifn = cfcifn;
    }

    public String getCfvipi() {
        return cfvipi;
    }

    public void setCfvipi(String cfvipi) {
        this.cfvipi = cfvipi;
    }

    public String getCfvipc() {
        return cfvipc;
    }

    public void setCfvipc(String cfvipc) {
        this.cfvipc = cfvipc;
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

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
