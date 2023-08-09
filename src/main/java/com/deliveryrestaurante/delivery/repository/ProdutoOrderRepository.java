package com.deliveryrestaurante.delivery.repository;

import com.deliveryrestaurante.delivery.model.ProdutoOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoOrderRepository extends JpaRepository<ProdutoOrder, Integer> {

    List<ProdutoOrder> findByCliente(Integer customerID);
}
