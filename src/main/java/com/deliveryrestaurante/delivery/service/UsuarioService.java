package com.deliveryrestaurante.delivery.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.deliveryrestaurante.delivery.data.UsuarioDataDetail;
import com.deliveryrestaurante.delivery.dto.AppUserDTO;
import com.deliveryrestaurante.delivery.model.AppUser;
import com.deliveryrestaurante.delivery.repository.UsuarioRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class UsuarioService {

    public final UsuarioRepository userRepository;

    public final PasswordEncoder passwordEncoder;

    final AuthenticationManager authenticationManager;

    public static final int EXPIRE_TOKEN = 600_0000;

    public static final String PASSWORD_TOKEN = "d6adefb7-359b-4bd5-80b8-4b9be27b04f6";

    public UsuarioService(UsuarioRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void save (AppUser appUser){
        Optional<AppUser> user = userRepository.findByUsername(appUser.getUsername());

        if (user.isPresent()){
            throw new Error("User already exists!");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);
    }

    public ResponseEntity<UsuarioDataDetail> signin(AppUserDTO appUserDTO){
        Authentication authenticate =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUserDTO.getUsername(), appUserDTO.getPassword(), new ArrayList<>()));

        UsuarioDataDetail userData = (UsuarioDataDetail) authenticate.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC512(PASSWORD_TOKEN);
        String access_token = JWT.create().withSubject(userData.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TOKEN)).sign(algorithm);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, access_token)
                .body(userData);
    }
}
