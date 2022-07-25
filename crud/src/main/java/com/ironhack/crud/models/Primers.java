package com.ironhack.crud.models;


public class Primers {

    private Integer id;

    private String name;

    private String forwardSequence;

    private String reverseSequence;

    public Primers(String name, String forwardSequence, String reverseSequence) {
        this.name = name;
        this.forwardSequence = forwardSequence;
        this.reverseSequence = reverseSequence;
    }

    public Primers(){}

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
