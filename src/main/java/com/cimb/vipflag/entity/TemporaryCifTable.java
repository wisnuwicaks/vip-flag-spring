package com.cimb.vipflag.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TemporaryCifTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long CFCIFN;

    private String CFVIPI;
    private String CFVIPC;
    private LocalDateTime createdDate;

    private int makerId;
    private String makerUsername;
    private int fileId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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



    public int getMakerId() {
        return makerId;
    }

    public void setMakerId(int makerId) {
        this.makerId = makerId;
    }

    public String getMakerUsername() {
        return makerUsername;
    }

    public void setMakerUsername(String makerUsername) {
        this.makerUsername = makerUsername;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
}
