package com.deliveryrestaurante.delivery.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.deliveryrestaurante.delivery.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTValidationFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATTRIBUTE = "Authorization";
    public static final String ATTRIBUTE_PREFIX = "Bearer ";



    public JWTValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    //recebe todas as requisições e determina se o usuário tem acesso
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String attribute = request.getHeader(HEADER_ATTRIBUTE);

        if (attribute == null || !attribute.startsWith(ATTRIBUTE_PREFIX)){
            chain.doFilter(request, response);
        }else {
            String token = attribute.replace(ATTRIBUTE_PREFIX, "");
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String username = JWT.require(Algorithm.HMAC512(UsuarioService.PASSWORD_TOKEN)).build().verify(token).getSubject();

        if (username == null){
            return null;
        }

    //Não precisamos da senha, pois o usuário já foi autenticado e o token é válido
        return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }
}
