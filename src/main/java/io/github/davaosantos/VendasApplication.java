package io.github.davaosantos;

import io.github.davaosantos.domain.entity.Cliente;
import io.github.davaosantos.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientesRepository){
        return args -> {
            clientesRepository.salvarCliente(new Cliente("David"));
            clientesRepository.salvarCliente(new Cliente("Fernanda"));
            clientesRepository.salvarCliente(new Cliente("Ang"));

            List<Cliente> clienteList = clientesRepository.obterTodos();

            clienteList.forEach(System.out :: println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
