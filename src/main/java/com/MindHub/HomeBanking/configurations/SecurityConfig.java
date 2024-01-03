package com.MindHub.HomeBanking.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration//notacion para decir que hay mas de un bean
@EnableWebSecurity//sirve para activar la seguridad para poder crear los filtros
public class SecurityConfig {
    @Bean//genera una instancia de este metodo dento del contexto de spring y lo ejecuta al comienzo de la aplicacion
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{//modificando las reglas de la seguridad de la app

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/index.html", "style.css","/js/login.js", "/js/tailwind.config.js","/images/**").permitAll()
                .requestMatchers("/api/clients/current","/web/**","/js/**","/api/accounts/*/transactions","/api/clients/current/accounts","/api/clients/current/cards","/api/transactions").hasAuthority("CLIENT")
                .requestMatchers(HttpMethod.POST, "/api/clients").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/clients/current/accounts", "/api/clients/current/cards","/api/transactions").hasAuthority("CLIENT")
                .anyRequest().denyAll());

        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());//se deshabilita la seguridad para ver la base de datos

        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
                frameOptionsConfig -> frameOptionsConfig.disable()));// se saca por que le h2 puede intentar cargarse como un iframe

        http.formLogin(formLogin -> formLogin
                .loginPage("/index.html")//le indica donde se va a ver el formulario
                .loginProcessingUrl("/api/login")//genera el endpoint para hacer las peticiones
                .usernameParameter("email")//indica que va a pedir el login son los queryParams
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> clearAuthenticationAttributes(request))
                .failureHandler((request, response, exception) -> response.sendError(401))
                .permitAll());

        http.logout(httpSecurityLogoutConfigurer ->
                httpSecurityLogoutConfigurer
                        .logoutUrl("/api/logout")
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                        .deleteCookies("JSESSIONID"));

        http.exceptionHandling( exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> response.sendError(403)));

        http.rememberMe(Customizer.withDefaults());

        return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }
}
