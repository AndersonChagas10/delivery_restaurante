package com.deliveryrestaurante.delivery.controller;

import com.deliveryrestaurante.delivery.dto.DeliveryDTO;
import com.deliveryrestaurante.delivery.model.Delivery;
import com.deliveryrestaurante.delivery.model.ProdutoOrder;
import com.deliveryrestaurante.delivery.repository.DeliveryRepository;
import com.deliveryrestaurante.delivery.repository.ProdutoOrderRepository;
import com.deliveryrestaurante.delivery.service.DeliveryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
@SecurityRequirement(name = "delivery")
public class DeliveryController {

    @Autowired
    public DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService deliveryService;

    @Autowired
    public ProdutoOrderRepository productOrderRepository;

    @PostMapping("/register")
    public void register (@RequestBody DeliveryDTO deliveryDTO) throws Exception {
        ProdutoOrder produtoOrder = productOrderRepository.findById(deliveryDTO.getProductOrder().getId()).get();
        if (produtoOrder != null)
            deliveryService.save(deliveryDTO);
        else
            throw new Exception("Order could not be registered");
    }

    @GetMapping("/list")
    public List<Delivery> findAll (){
        return deliveryRepository.findAll();
    }

    @DeleteMapping("/delete/{deliveryID}")
    public void delete (@PathVariable("deliveryID") Integer deliveryID){
        deliveryRepository.deleteById(deliveryID);
    }

    @PutMapping("/update/{deliveryID}")
    public void update (@PathVariable("deliveryID") Integer deliveryID, @RequestBody DeliveryDTO deliveryDTO) throws Exception {
        Delivery delivery = deliveryRepository.findById(deliveryID).orElse(null);
        if (delivery != null){
            deliveryService.update(delivery, deliveryDTO );
        }else{
            throw new Exception("Delivery not found");
        }
    }
}
