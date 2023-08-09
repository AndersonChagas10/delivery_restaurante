package com.deliveryrestaurante.delivery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name = "order_generator", sequenceName = "order_seq")
    @Column(name = "ID")
    private Integer id;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime momentRequested;

    @Column(length = 300, nullable = false)
    private String description;

    @Column(name = "sub_total", nullable = false)
    private double subTotal;

    @Column(nullable = false)
    private double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrdemStatus status;

    @Transient
    private List<Produto> products;

    @OneToOne(mappedBy = "produtoOrder", cascade = CascadeType.ALL, orphanRemoval= true)
    private Delivery delivery;

    @ManyToOne
    private Cliente cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getMomentRequested() {
        return momentRequested;
    }

    public void setMomentRequested(LocalDateTime momentRequested) {
        this.momentRequested = momentRequested;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrdemStatus getStatus() {
        return status;
    }

    public void setStatus(OrdemStatus status) {
        this.status = status;
    }

    @JsonManagedReference
    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @JsonBackReference
    public Cliente getCustomer() {
        return cliente;
    }

    public void setCustomer(Cliente customer) {
        this.cliente = customer;
    }

    public List<Produto> getProducts() {
        return products;
    }

    public void setProducts(List<Produto> products) {
        this.products = products;
    }
}
