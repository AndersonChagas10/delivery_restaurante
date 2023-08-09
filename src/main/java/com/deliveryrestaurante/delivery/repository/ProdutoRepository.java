package com.deliveryrestaurante.delivery.repository;

import com.deliveryrestaurante.delivery.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
