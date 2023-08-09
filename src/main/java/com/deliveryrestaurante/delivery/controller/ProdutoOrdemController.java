package com.deliveryrestaurante.delivery.controller;


import com.deliveryrestaurante.delivery.dto.ProdutoOrderDTO;
import com.deliveryrestaurante.delivery.model.ProdutoOrder;
import com.deliveryrestaurante.delivery.repository.ProdutoOrderRepository;
import com.deliveryrestaurante.delivery.service.ProdutoOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordem")
@SecurityRequirement(name = "delivery")
public class ProdutoOrdemController {

    @Autowired
    public ProdutoOrderRepository productOrderRepository;

    @Autowired
    public ProdutoOrderService productOrderService;

    @PostMapping("/register")
    public void register (@RequestBody ProdutoOrderDTO productOrderDTO) throws Exception {
        if (productOrderDTO.getSubTotal() <= productOrderDTO.getTotal() && productOrderDTO.getCustomer() != null){
            productOrderService.save(productOrderDTO);
        }else {
            throw new Exception("Order could not be registered");
        }
    }

    @GetMapping("/list")
    public List<ProdutoOrder> findAll(){
        return productOrderRepository.findAll();
    }

    @GetMapping("/{customer_id}")
    public List<ProdutoOrder> findByCustomerID(@PathVariable("cliente_id") Integer clienteID){
        return productOrderRepository.findByCliente(clienteID);
    }

    @DeleteMapping("/delete/{orderID}")
    public void deleteOrder (@PathVariable("orderID") Integer productOrderID){
        productOrderRepository.deleteById(productOrderID);
    }

    @PutMapping("/update/{orderID}")
    public void updateOrder (@PathVariable("orderID") Integer orderID, @RequestBody ProdutoOrderDTO productOrderDTO) throws Exception {
        ProdutoOrder produtoOrder = productOrderRepository.findById(orderID).orElse(null);
        if (produtoOrder != null){
            productOrderService.update(produtoOrder, productOrderDTO);
        }else{
            throw new Exception("Product order not Found");
        }
    }
}
