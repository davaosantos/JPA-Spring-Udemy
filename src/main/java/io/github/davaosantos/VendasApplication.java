package io.github.davaosantos;

import io.github.davaosantos.domain.entity.Cliente;
import io.github.davaosantos.domain.entity.Pedido;
import io.github.davaosantos.domain.repository.ClientesRepository;
import io.github.davaosantos.domain.repository.ItemsPedidoRepository;
import io.github.davaosantos.domain.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired ClientesRepository clientesRepository,
            @Autowired PedidosRepository pedidosRepository,
            @Autowired ItemsPedidoRepository itemsPedidoRepository
    ) {
        return args -> {

            //Salvando clientes
            clientesRepository.save(new Cliente("David"));
            clientesRepository.save(new Cliente("Fernanda"));
            clientesRepository.save(new Cliente("Ang"));

            List<Cliente> clienteList = clientesRepository.findAll();

            Cliente clienteBuscado = clienteList.get(0); // pega o david
            Pedido pedido = new Pedido(clienteBuscado); // Pega o David
            pedido.setDtPedido(LocalDate.now());
            pedido.setTotal(BigDecimal.valueOf(200));

            pedidosRepository.save(pedido);

//            Cliente clienteComPedido = clientesRepository.findClienteFetchPedidos(clienteBuscado.getId());
//            System.out.println(clienteComPedido);
//            System.out.println(clienteComPedido.getPedidos());

            pedidosRepository.findByCliente(clienteBuscado).forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
