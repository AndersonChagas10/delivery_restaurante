package com.deliveryrestaurante.delivery.service;

import com.deliveryrestaurante.delivery.dto.DeliveryDTO;
import com.deliveryrestaurante.delivery.model.Delivery;
import com.deliveryrestaurante.delivery.model.DeliveryStatus;
import com.deliveryrestaurante.delivery.model.OrdemStatus;
import com.deliveryrestaurante.delivery.model.ProdutoOrder;
import com.deliveryrestaurante.delivery.repository.DeliveryRepository;
import com.deliveryrestaurante.delivery.repository.ProdutoOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class DeliveryService {

    @Autowired
    public DeliveryRepository deliveryRepository;

    @Autowired
    public ProdutoOrderRepository productOrderRepository;

    public void save(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setTax(deliveryDTO.getTax());
        delivery.setDeliveryStatus(deliveryDTO.getDeliveryStatus());

        ProdutoOrder order = productOrderRepository.findById(deliveryDTO.getProductOrder().getId()).get();
        order.setTotal(order.getSubTotal() + delivery.getTax());
        delivery.setProductOrder(order);

        if (delivery.getDeliveryStatus() == DeliveryStatus.ENTREGUE){
            delivery.setTimeDelivered(LocalDateTime.now());
            order.setStatus(OrdemStatus.CONCLUIDO);
            productOrderRepository.save(order);
        }

        deliveryRepository.save(delivery);
    }

//    public void save(DeliveryDTO deliveryDTO, Integer order_ID) {
//        Delivery delivery = new Delivery();
//        delivery.setTax(deliveryDTO.getTax());
//        delivery.setDeliveryStatus(deliveryDTO.getDeliveryStatus());
//
//        ProductOrder order = productOrderRepository.findById(order_ID).orElse(null);
//        order.setTotal(order.getSubTotal() + delivery.getTax());
//        delivery.setProductOrder(order);
//
//        if (delivery.getDeliveryStatus() == DeliveryStatus.DELIVERED){
//            delivery.setTimeDelivered(LocalDateTime.now());
//            order.setStatus(OrderStatus.CONCLUDED);
//            productOrderRepository.save(order);
//        }
//
//        deliveryRepository.save(delivery);
//    }

    public void update(Delivery delivery, DeliveryDTO deliveryDTO) {
        ProdutoOrder produtoOrder = productOrderRepository.findById( delivery.getProductOrder().getId()).get();

        if (delivery.getTax() != deliveryDTO.getTax() && produtoOrder != null){
            delivery.setTax(deliveryDTO.getTax());
            produtoOrder.setTotal(produtoOrder.getSubTotal() + deliveryDTO.getTax());
        }

        if (deliveryDTO.getDeliveryStatus() == DeliveryStatus.ENTREGUE && delivery.getDeliveryStatus() != DeliveryStatus.ENTREGUE){
            delivery.setTimeDelivered(LocalDateTime.now());
            delivery.setDeliveryStatus(deliveryDTO.getDeliveryStatus());

            produtoOrder.setStatus(OrdemStatus.CONCLUIDO);
        }
        productOrderRepository.save(produtoOrder);
        deliveryRepository.save(delivery);
    }
}
