package com.ironhack.ordersservice.model;

import com.ironhack.ordersservice.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer primersId;

    private BigDecimal price;

    private Status status;

    public Order(Integer userId, Integer primersId, BigDecimal price) {
        this.userId = userId;
        this.primersId = primersId;
        this.price = price;
        this.status=Status.PROCESSED;
        // by default, each time an order is created the status is "procesed"
        // and only changes to delivered when the scientist receives the primers
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPrimersId() {
        return primersId;
    }

    public void setPrimersId(Integer primersId) {
        this.primersId = primersId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
