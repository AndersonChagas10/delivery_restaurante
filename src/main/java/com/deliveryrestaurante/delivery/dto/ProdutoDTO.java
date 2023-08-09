package com.deliveryrestaurante.delivery.dto;

public class ProdutoDTO {

    private double preco;
    private String name;

    public double getPrice() {
        return preco;
    }

    public void setPrice(double price) {
        this.preco = preco;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
