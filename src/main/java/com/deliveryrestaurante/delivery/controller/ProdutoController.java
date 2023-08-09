package com.deliveryrestaurante.delivery.controller;

import com.deliveryrestaurante.delivery.dto.ProdutoDTO;
import com.deliveryrestaurante.delivery.model.Produto;
import com.deliveryrestaurante.delivery.repository.ProdutoRepository;
import com.deliveryrestaurante.delivery.service.ProdutoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@SecurityRequirement(name = "delivery")
public class ProdutoController {

    @Autowired
    public ProdutoRepository productRepository;

    @Autowired
    public ProdutoService productService;


    @PostMapping("/register")
    public void register (@RequestBody Produto product){
        productRepository.save(product);
    }

    @GetMapping("/list")
    public List<Produto> findAll(){
        return productRepository.findAll();
    }

    @DeleteMapping("/delete/{productID}")
    public void delete (@PathVariable("productID") Integer productID){
        productRepository.deleteById(productID);
    }

    @PutMapping("/update/{productID}")
    public void update (@PathVariable("productID") Integer productID, @RequestBody ProdutoDTO productDTO) throws Exception {
        Produto product = productRepository.findById(productID).orElse(null);
        if (product != null) {
            productService.update(product, productDTO);
        }else {
            throw new Exception("Delivery not found");
        }
    }
}
