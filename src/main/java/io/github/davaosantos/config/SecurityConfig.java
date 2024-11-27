package io.github.davaosantos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //$2a$12$DgJnUyI3z.8lDaSxpMEycOJjgSCuq6OWcxR/fU7tQ4/m5Kd2KZ7Ku
   // $2a$12$L9rkliB.japWWo1Rva3PtOrneDrA4me/dyU/9jxYaPVfYEnyWZsii
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };

    //Autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    //Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
