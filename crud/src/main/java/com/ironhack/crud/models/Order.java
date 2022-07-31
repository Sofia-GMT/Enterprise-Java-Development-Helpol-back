package com.ironhack.crud.models;


import com.ironhack.crud.enums.StatusOrder;

import java.math.BigDecimal;

public class Order {

    private Integer id;

    private Integer userId;

    private Integer primersId;

    private BigDecimal price;

    private Integer concentration;

    private StatusOrder statusOrder;

    public Order(){}

    public Order(Integer userId, Integer primersId, Integer concentration) {
        this.userId = userId;
        this.primersId = primersId;
        this.concentration = concentration;
    }

    public Order(Integer userId, Integer primersId, BigDecimal price, Integer concentration) {
        this.userId = userId;
        this.primersId = primersId;
        this.price = price;
        this.concentration=concentration;
        this.statusOrder = StatusOrder.PROCESSED;
        // by default, each time an order is created the status is "processed"
        // and only changes to delivered when the scientist receives the primers
    }

    public Integer getConcentration() {
        return concentration;
    }

    public void setConcentration(Integer concentration) {
        this.concentration = concentration;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
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
