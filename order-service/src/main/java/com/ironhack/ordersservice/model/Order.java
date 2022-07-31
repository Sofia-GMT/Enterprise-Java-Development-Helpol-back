package com.ironhack.ordersservice.model;

import com.ironhack.ordersservice.enums.StatusOrder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer primersId;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    public Order(){}

    public Order(Integer userId, Integer primersId, BigDecimal price) {
        this.userId = userId;
        this.primersId = primersId;
        this.price = price;
        this.statusOrder = StatusOrder.PROCESSED;
        // by default, each time an order is created the status is "processed"
        // and only changes to delivered when the scientist receives the primers
    }

    public StatusOrder getStatus() {
        return statusOrder;
    }

    public void setStatus(StatusOrder statusOrder) {
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
