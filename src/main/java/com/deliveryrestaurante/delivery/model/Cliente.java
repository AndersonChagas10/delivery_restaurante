package com.deliveryrestaurante.delivery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_generator")
    @SequenceGenerator(name = "cliente_generator", sequenceName = "cliente_seq")
    @Column(name = "ID")
    private Integer id;

    @Column(length = 80, name = "cliente_name", nullable = false)
    private String name;

    @Column
    private String telefoneNumero;

    @Embedded
    private Location address;

    @OneToMany(targetEntity = ProdutoOrder.class, cascade = CascadeType.ALL, orphanRemoval= true, mappedBy = "cliente")
    private List<ProdutoOrder> produtoOrders;

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

    public String getTelefoneNumero() {
        return telefoneNumero;
    }

    public void setTelefoneNumero(String telefoneNumero) {
        this.telefoneNumero = telefoneNumero;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    @JsonManagedReference
    public List<ProdutoOrder> getProductOrders() {
        return produtoOrders;
    }

    public void setProductOrders(List<ProdutoOrder> produtoOrders) {
        this.produtoOrders = produtoOrders;
    }
}
