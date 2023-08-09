package com.deliveryrestaurante.delivery.dto;

import com.deliveryrestaurante.delivery.model.DeliveryStatus;
import com.deliveryrestaurante.delivery.model.ProdutoOrder;

public class DeliveryDTO {

    private double tax;
    private DeliveryStatus deliveryStatus;
    private ProdutoOrder produtoOrder;

    public ProdutoOrder getProductOrder() {
        return produtoOrder;
    }

    public void setProductOrder(ProdutoOrder produtoOrder) {
        this.produtoOrder = produtoOrder;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
