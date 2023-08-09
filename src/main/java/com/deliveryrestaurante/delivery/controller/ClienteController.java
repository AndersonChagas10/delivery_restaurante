package com.deliveryrestaurante.delivery.controller;


import com.deliveryrestaurante.delivery.dto.ClienteDTO;
import com.deliveryrestaurante.delivery.model.Cliente;
import com.deliveryrestaurante.delivery.repository.ClienteRepository;
import com.deliveryrestaurante.delivery.service.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@SecurityRequirement(name = "deliveryapi")
public class ClienteController {


    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public ClienteService clienteService;

    @PostMapping("/register")
    public void register(@RequestBody ClienteDTO clienteDTO) {
        clienteService.save(clienteDTO);
    }

    @GetMapping("/list")
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{customerID}")
    public Optional<Cliente> findCustomer(@PathVariable("customerID") Integer customerID) {
        return clienteRepository.findById(customerID);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer customerID) {
        clienteRepository.deleteById(customerID);
    }

    @PutMapping("/update/{customerID}")
    public void update(@PathVariable("customerID") Integer customerID, @RequestBody ClienteDTO clienteDTO) throws Exception {
        Cliente cliente = clienteRepository.findById(customerID).orElse(null);
        if (cliente != null) {
            clienteService.update(cliente , clienteDTO);
        } else {
            throw new Exception("Customer not found");
        }
    }
}