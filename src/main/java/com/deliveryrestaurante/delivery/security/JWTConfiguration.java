package com.deliveryrestaurante.delivery.security;


import com.deliveryrestaurante.delivery.service.UsuarioDetalheServiceImplemented;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.Filter;


@EnableWebSecurity
@Configuration
@SecurityScheme(name="delivery", scheme= "Bearer", bearerFormat = "JWT", type= SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, description = "Insira o Token")
public class JWTConfiguration extends WebSecurityConfigurerAdapter {

    private final UsuarioDetalheServiceImplemented userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfiguration(UsuarioDetalheServiceImplemented userDetailService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()

                 .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario/salvar").permitAll()
                .antMatchers(HttpMethod.GET, "/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger.html").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/index.html").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/swagger-ui.css").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/swagger-ui-bundle.js").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/swagger-ui-standalone-preset.js").permitAll()
                .antMatchers(HttpMethod.GET, "swagger-ui/favicon-16x16.png").permitAll()
                .antMatchers(HttpMethod.GET, "/api-docs/swagger-config").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/favicon-16x16.png").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/favicon-32x32.png").permitAll()
                .anyRequest()
                .authenticated().and().addFilter((Filter) new JWTAutenticarFilter(authenticationManager()))
                .addFilter((Filter) new JWTValidationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    //We can maintain blocked to external domains
//    @Bean
//    CorsConfigurationSource corsConfigurationSource () {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return source;
//    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;

}
}