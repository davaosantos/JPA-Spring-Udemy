package io.github.davaosantos.config;

import io.github.davaosantos.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class Config {

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("RODANDO A CONFIG DE DESENVOLVIMENTO");
        };
    };

}
