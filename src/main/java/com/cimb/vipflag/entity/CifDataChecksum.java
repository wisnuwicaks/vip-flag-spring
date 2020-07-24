package com.cimb.vipflag.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class CifDataChecksum {
    @Id
    private int cfcifn;

    private String cfvipi;
    private String cfvipc;
    private LocalDateTime createdDate;
    private LocalDateTime approvalDate;
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

    @Override
    public String toString() {
        return  "cfcifn : " +cfcifn+
                "cfvipi : " +cfvipi+
                "cfvipc : " +cfvipc+
                "approvalStatus : " +approvalStatus;

    }
}
