package com.cimb.vipflag.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CifDataUploaded {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long CFCIFN;

    private String CFVIPI;
    private String CFVIPC;
    private LocalDateTime createdDate;
    private  LocalDateTime approvalDate;

    private String approvalStatus;

    public long getCFCIFN() {
        return CFCIFN;
    }

    public void setCFCIFN(long CFCIFN) {
        this.CFCIFN = CFCIFN;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
