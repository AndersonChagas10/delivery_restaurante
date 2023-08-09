package com.deliveryrestaurante.delivery.repository;

import com.deliveryrestaurante.delivery.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);
}
