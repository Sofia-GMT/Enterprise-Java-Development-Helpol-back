package com.ironhack.primersservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Primers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String forwardSequence;

    private String reverseSequence;

    public Primers(String name, String forwardSequence, String reverseSequence) {
        this.name = name;
        this.forwardSequence = forwardSequence;
        this.reverseSequence = reverseSequence;
    }

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

    public String getForwardSequence() {
        return forwardSequence;
    }

    public void setForwardSequence(String forwardSequence) {
        this.forwardSequence = forwardSequence;
    }

    public String getReverseSequence() {
        return reverseSequence;
    }

    public void setReverseSequence(String reverseSequence) {
        this.reverseSequence = reverseSequence;
    }
}
