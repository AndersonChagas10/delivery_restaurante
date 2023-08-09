package com.deliveryrestaurante.delivery.dto;

import com.deliveryrestaurante.delivery.model.Cliente;
import com.deliveryrestaurante.delivery.model.OrdemStatus;
import com.deliveryrestaurante.delivery.model.Produto;

import java.util.List;

public class ProdutoOrderDTO {

    private String description;
    private double subTotal;
    private double total;
    private OrdemStatus status;
    private List<Produto> products;
    private Cliente customer;

    public Cliente getCustomer() {
        return customer;
    }

    public void setCustomer(Cliente customer) {
        this.customer = customer;
    }

    public List<Produto> getProducts() {
        return products;
    }

    public void setProducts(List<Produto> products) {
        this.products = products;
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
}
