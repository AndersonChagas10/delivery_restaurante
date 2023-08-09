package com.deliveryrestaurante.delivery.service;

import com.deliveryrestaurante.delivery.dto.ClienteDTO;
import com.deliveryrestaurante.delivery.model.Cliente;
import com.deliveryrestaurante.delivery.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    public void save(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();

        cliente.setName(clienteDTO.getName());
        cliente.setAddress(clienteDTO.getAddress());
        cliente.setTelefoneNumero(clienteDTO.getPhoneNumber());

        clienteRepository.save(cliente);
    }

    public void update(Cliente customer, ClienteDTO customerDTO) {
        customer.setTelefoneNumero(customerDTO.getPhoneNumber());
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());

        clienteRepository.save(customer);
    }

}
