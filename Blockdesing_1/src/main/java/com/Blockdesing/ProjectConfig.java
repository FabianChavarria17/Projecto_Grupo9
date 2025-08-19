package com.Blockdesing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                // Páginas públicas
                .requestMatchers("/", "/index", "/login", "/registro", "/recuperarPassword", 
                        "/verificarCuenta", "/css/**", "/js/**", "/img/**", "/webjars/**", "/error"
                ).permitAll()
                // Solo ADMIN puede ver la lista de usuarios
                .requestMatchers("/usuarios/**").hasRole("ADMIN")
                // El resto requiere login
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login") // tu controlador de login
                .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("correo") //
                .passwordParameter("password")
                .failureUrl("/login?error=true")
                .permitAll()
                );

        return http.build();
    }

}
