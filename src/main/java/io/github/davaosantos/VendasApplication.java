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

            //Salvando clientes
            clientesRepository.save(new Cliente("David"));
            clientesRepository.save(new Cliente("Fernanda"));
            clientesRepository.save(new Cliente("Ang"));

            System.out.println("Vendo os clientes");
            List<Cliente> clienteList = clientesRepository.findAll();
            clienteList.forEach(System.out::println);

            System.out.println("Tentando deletar david");
            clientesRepository.deleteByNome("David");

            System.out.println("Deletado , mostrando clientes agora");
            clienteList.forEach(System.out::println);

            clienteList = clientesRepository.encontrarPorNomeJpql("Fernanda");
            clienteList.forEach(System.out::println);

            System.out.println("SQL NATIVO ------------");
            clienteList = clientesRepository.encontrarPorNomeNativo("Fernanda");
            clienteList.forEach(System.out::println);

            boolean exists = clientesRepository.existsByNome("David");
            System.out.println("Existe um cliente com o nome David ? " + exists);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
