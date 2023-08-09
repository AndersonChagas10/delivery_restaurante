package com.deliveryrestaurante.delivery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_generator")
    @SequenceGenerator(name = "delivery_generator", sequenceName = "delivery_seq")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "delivery_tax", nullable = false)
    private double tax;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private LocalDateTime timeDelivered;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne
    @JoinColumn(unique = true, name = "order_id")
    private ProdutoOrder productOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public LocalDateTime getTimeDelivered() {
        return timeDelivered;
    }

    public void setTimeDelivered(LocalDateTime timeDelivered) {
        this.timeDelivered = timeDelivered;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @JsonBackReference
    public ProdutoOrder getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(ProdutoOrder productOrder) {
        this.productOrder = productOrder;
    }
}
