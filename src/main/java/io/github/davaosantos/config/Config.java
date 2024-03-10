package io.github.davaosantos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name = "appName")
    public String appName(){
        return "Sistema de vendas";
    }

}
