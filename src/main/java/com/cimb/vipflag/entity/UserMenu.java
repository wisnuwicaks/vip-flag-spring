package com.cimb.vipflag.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String groupMenuName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userMenu", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupMenuName() {
        return groupMenuName;
    }

    public void setGroupMenuName(String groupMenuName) {
        this.groupMenuName = groupMenuName;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
