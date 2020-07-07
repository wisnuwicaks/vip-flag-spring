package com.cimb.vipflag.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CreatedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cifNumber;

    private String vipIndicator;
    private String subVipIndicator;
    private Date createdDate;
    private String statusUpload;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "maker_id")
    private User user;

    public int getCifNumber() {
        return cifNumber;
    }

    public void setCifNumber(int cifNumber) {
        this.cifNumber = cifNumber;
    }

    public String getVipIndicator() {
        return vipIndicator;
    }

    public void setVipIndicator(String vipIndicator) {
        this.vipIndicator = vipIndicator;
    }

    public String getSubVipIndicator() {
        return subVipIndicator;
    }

    public void setSubVipIndicator(String subVipIndicator) {
        this.subVipIndicator = subVipIndicator;
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
