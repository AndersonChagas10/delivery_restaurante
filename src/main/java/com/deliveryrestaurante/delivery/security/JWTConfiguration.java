package com.deliveryrestaurante.delivery.security;


import com.deliveryrestaurante.delivery.service.UsuarioDetalheServiceImplemented;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

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

            //diz ao Spring como procurar os usuários
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Falsificação de solicitação entre sites, embora impeça algumas chamadas indesejadas do navegador sem intervenção do usuário final
        //Desativamos, porque nosso aplicativo é de autorização baseada em token, portanto, não precisamos do CSRF ativado
        http.csrf().disable();

        //permitir que todos acessem o mapeamento do usuário (registrar usuário)
        http.authorizeRequests().antMatchers("/user/register").permitAll();
        http.authorizeRequests().antMatchers("/user/login").permitAll();
        http.authorizeRequests().antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();


        //permite que todos façam requisições de aplicação se forem autenticados
        http.authorizeRequests().anyRequest().authenticated();

//        //valida o token passado nas requisições
           http.addFilter((Filter) new JWTValidationFilter(authenticationManager()));

        //Não salva a sessão do usuário no banco de dados
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}