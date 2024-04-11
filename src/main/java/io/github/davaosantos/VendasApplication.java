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

            //Buscanto todos os clientes
            System.out.println("Buscanto todos os clientes");
            List<Cliente> clienteList = clientesRepository.obterTodos();
            clienteList.forEach(System.out :: println);

            //Atualizando
            System.out.println("Atualizando clientes");
            clienteList.forEach(cliente -> {
                cliente.setNome(cliente.getNome() + " atualizado.");
                clientesRepository.atualizar(cliente);
            });

            //Obtenho todos após o update
            System.out.println("Lista de clientes após o update");
            clienteList.forEach(System.out :: println);

            //Busca por nome
            System.out.println("Busca de cliente por nome");
            clientesRepository.buscaClientesPorNome("David").forEach(System.out::println);

            //Deletando o cliente
            System.out.println("Deletando clientes");
            clienteList.forEach(cliente -> {
                clientesRepository.deletarClientePorId(cliente.getId());
            });

            if (clientesRepository.obterTodos().isEmpty()){
                System.out.println("Não encontrado clientes");
            }else{
                clienteList.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
