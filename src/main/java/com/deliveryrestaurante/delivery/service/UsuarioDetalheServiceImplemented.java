package com.deliveryrestaurante.delivery.service;

import com.deliveryrestaurante.delivery.data.UsuarioDataDetail;
import com.deliveryrestaurante.delivery.model.AppUser;
import com.deliveryrestaurante.delivery.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UsuarioDetalheServiceImplemented implements UserDetailsService {
    public final UsuarioRepository userRepository;

    public UsuarioDetalheServiceImplemented(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByUsername(username);

        if (!user.isPresent()){
            throw new UsernameNotFoundException("User: " + username + " was not found");
        }
        //we may return a User from the class, not our user
        //do we implement an array of simpleGrantedAuthority
        return new UsuarioDataDetail(user);
    }
}

