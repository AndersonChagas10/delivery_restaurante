package com.deliveryrestaurante.delivery.repository;

import com.deliveryrestaurante.delivery.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
