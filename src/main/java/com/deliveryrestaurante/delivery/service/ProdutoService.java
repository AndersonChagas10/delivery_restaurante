package com.deliveryrestaurante.delivery.service;


import com.deliveryrestaurante.delivery.dto.ProdutoDTO;
import com.deliveryrestaurante.delivery.model.Produto;
import com.deliveryrestaurante.delivery.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    public ProdutoRepository productRepository;

    public void update(Produto product, ProdutoDTO productDTO) {
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        productRepository.save(product);
    }
}
