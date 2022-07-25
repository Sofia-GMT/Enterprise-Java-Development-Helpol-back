package com.ironhack.crud.models;

import com.ironhack.crud.enums.Status;

public class User {

    private Integer id;

    private String name;

    private Integer IdInstitution;

    private Status status;


    public User(String name, int idInstitution) {
        this.name = name;
        IdInstitution = idInstitution;
        this.status = Status.PENDING;
        // by default, each time a user is created the status is "pending validation"
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
        return IdInstitution;
    }

    public void setIdInstitution(Integer idInstitution) {
        IdInstitution = idInstitution;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
