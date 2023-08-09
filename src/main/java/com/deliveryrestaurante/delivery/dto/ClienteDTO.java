package com.deliveryrestaurante.delivery.dto;

import com.deliveryrestaurante.delivery.model.Location;

public class ClienteDTO {

    private String name;
    private String telefoneNumber;
    private Location address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return telefoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.telefoneNumber = telefoneNumber;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }
}
