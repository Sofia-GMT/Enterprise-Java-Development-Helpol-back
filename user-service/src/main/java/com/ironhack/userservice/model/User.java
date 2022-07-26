package com.ironhack.userservice.model;

import com.ironhack.userservice.enums.StatusUser;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;

    private Integer idInstitution;

    @Enumerated
    private StatusUser statusUser;


    public User(String name, int idInstitution, String password) {
        this.name = name;
        this.idInstitution = idInstitution;
        this.password=password;
        this.statusUser = StatusUser.PENDING;
        // by default, each time a user is created the status is "pending validation"
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusUser getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(StatusUser statusUser) {
        this.statusUser = statusUser;
    }

    public User(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdInstitution() {
        return idInstitution;
    }

    public void setIdInstitution(Integer idInstitution) {
        this.idInstitution = idInstitution;
    }

    public StatusUser getStatus() {
        return statusUser;
    }

    public void setStatus(StatusUser statusUser) {
        this.statusUser = statusUser;
    }

}
