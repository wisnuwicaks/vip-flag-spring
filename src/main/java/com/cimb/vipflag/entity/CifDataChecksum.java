package com.cimb.vipflag.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CifDataChecksum {
    @Id
    private int CFCIFNtemp;

    private String CFVIPI;
    private String CFVIPC;
    private LocalDateTime createdDate;
    private LocalDateTime approvalDate;
    private String approvalStatus;

    public int getCFCIFNtemp() {
        return CFCIFNtemp;
    }

    public void setCFCIFNtemp(int CFCIFNtemp) {
        this.CFCIFNtemp = CFCIFNtemp;
    }

    public String getCFVIPI() {
        return CFVIPI;
    }

    public void setCFVIPI(String CFVIPI) {
        this.CFVIPI = CFVIPI;
    }

    public String getCFVIPC() {
        return CFVIPC;
    }

    public void setCFVIPC(String CFVIPC) {
        this.CFVIPC = CFVIPC;
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
        return  "cfcifn : " + CFCIFNtemp +
                "cfvipi : " +CFVIPI+
                "cfvipc : " +CFVIPC+
                "approvalStatus : " +approvalStatus;

    }
}
