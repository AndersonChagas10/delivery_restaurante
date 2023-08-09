package com.deliveryrestaurante.delivery.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.deliveryrestaurante.delivery.data.UsuarioDataDetail;
import com.deliveryrestaurante.delivery.model.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    public static final long TOKEN_EXPIRACAO = 9000000L;
    public static final String TOKEN_SENHA = "daafe017-b561-432a-8b37-3c88b1577f22";

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            AppUser usuario = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getPassword(), usuario.getPassword(), new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario ", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UsuarioDataDetail usuarioData = (UsuarioDataDetail) authResult.getPrincipal();
        String token = JWT.create().withSubject(usuarioData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));
        String login = ((UserDetails) authResult.getPrincipal()).getUsername();
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("login", login);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            jsonObject.put("token", token);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().print(jsonObject);
        response.getWriter().flush();
    }
}