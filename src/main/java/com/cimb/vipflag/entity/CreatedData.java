package com.cimb.vipflag.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CreatedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cfcifn;

    private String cfvipi;
    private String cfvipc;
    private Date createdDate;
    private String statusUpload;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "maker_id")
    private User user;


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

    public String getStatusUpload() {
        return statusUpload;
    }

    public void setStatusUpload(String statusUpload) {
        this.statusUpload = statusUpload;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
