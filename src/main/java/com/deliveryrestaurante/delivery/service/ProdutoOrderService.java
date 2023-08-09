package com.deliveryrestaurante.delivery.service;

import com.deliveryrestaurante.delivery.dto.ProdutoOrderDTO;
import com.deliveryrestaurante.delivery.model.Produto;
import com.deliveryrestaurante.delivery.model.ProdutoOrder;
import com.deliveryrestaurante.delivery.repository.ClienteRepository;
import com.deliveryrestaurante.delivery.repository.ProdutoOrderRepository;
import com.deliveryrestaurante.delivery.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProdutoOrderService {

    @Autowired
    public ProdutoOrderRepository productOrderRepository;

    @Autowired
    public ClienteRepository customerRepository;

    @Autowired
    public ProdutoRepository productRepository;

    public void save(ProdutoOrderDTO productOrderDTO) {
        ProdutoOrder produtoOrder = new ProdutoOrder();

        produtoOrder.setStatus(productOrderDTO.getStatus());
        produtoOrder.setDescription(productOrderDTO.getDescription());
        produtoOrder.setMomentRequested(LocalDateTime.now());
        produtoOrder.setProducts(productOrderDTO.getProducts());
        produtoOrder.setCustomer(productOrderDTO.getCustomer());

        List<Produto> products = new ArrayList<>();
        productOrderDTO.getProducts().forEach(product -> products.add(productRepository.findById(product.getId()).get()));

        if (produtoOrder.getSubTotal() == 0){
            double productSum = products.stream().mapToDouble((Produto::getPrice)).sum();
            produtoOrder.setSubTotal(productSum);
        }else{
            produtoOrder.setSubTotal(productOrderDTO.getSubTotal());
            produtoOrder.setTotal(productOrderDTO.getTotal());
        }

        productOrderRepository.save(produtoOrder);
    }

//    public void save(ProductOrderDTO productOrderDTO, Integer customerID) {
//        ProductOrder productOrder = new ProductOrder();
//
//        productOrder.setStatus(productOrderDTO.getStatus());
//        productOrder.setDescription(productOrderDTO.getDescription());
//        productOrder.setMomentRequested(LocalDateTime.now());
//        productOrder.setProducts(productOrderDTO.getProducts());
//
//        List<Product> products = new ArrayList<>();
//        productOrderDTO.getProducts().forEach(product -> products.add(productRepository.findById(product.getId()).get()));
//
//        if (productOrder.getSubTotal() == 0){
//            double productSum = products.stream().mapToDouble((product -> product.getPrice())).sum();
//            productOrder.setSubTotal(productSum);
////            if (productOrderDTO.getDelivery() != null){
////                productOrder.setTotal(productSum + productOrderDTO.getDelivery().getTax());
////            }
//        }else{
//            productOrder.setSubTotal(productOrderDTO.getSubTotal());
//            productOrder.setTotal(productOrderDTO.getTotal());
//        }
//
//        Customer customer = customerRepository.findById(customerID).orElse(null);
//        if (customer != null) {
//            productOrder.setCustomer(customer);
//        }
//
//        productOrderRepository.save(productOrder);
//    }

    public void update(ProdutoOrder produtoOrder, ProdutoOrderDTO productOrderDTO) {
        produtoOrder.setStatus(productOrderDTO.getStatus());
        produtoOrder.setTotal(productOrderDTO.getTotal());
        produtoOrder.setSubTotal(productOrderDTO.getSubTotal());
        produtoOrder.setDescription(productOrderDTO.getDescription());

        productOrderRepository.save(produtoOrder);
    }

}
