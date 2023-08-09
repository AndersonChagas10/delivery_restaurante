package com.deliveryrestaurante.delivery.repository;

import com.deliveryrestaurante.delivery.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}
