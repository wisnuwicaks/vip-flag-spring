package com.cimb.vipflag.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class ApprovedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cifNumber;

    private Date approvalDate;
    private String vipIndicator;
    private String subVipIndicator;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "checker_id")
    private User user;

    public int getCifNumber() {
        return cifNumber;
    }

    public void setCifNumber(int cifNumber) {
        this.cifNumber = cifNumber;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
