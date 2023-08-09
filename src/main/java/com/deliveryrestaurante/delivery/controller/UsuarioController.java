package com.deliveryrestaurante.delivery.controller;


import com.deliveryrestaurante.delivery.data.UsuarioDataDetail;
import com.deliveryrestaurante.delivery.dto.AppUserDTO;
import com.deliveryrestaurante.delivery.model.AppUser;
import com.deliveryrestaurante.delivery.repository.UsuarioRepository;
import com.deliveryrestaurante.delivery.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "delivery")
public class UsuarioController {

    final UsuarioRepository userRepository;

    final UsuarioService userService;

    public UsuarioController(UsuarioRepository userRepository, UsuarioService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/list")
    @Operation(summary = "List All Users", security = @SecurityRequirement(name = "deliveryapi"))
    public List<AppUser> findAll(){
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public void register(@RequestBody AppUser appUser){
        userService.save(appUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDataDetail> validatePassword (@RequestBody AppUserDTO appUserDTO){
        return userService.signin(appUserDTO);
    }
}
