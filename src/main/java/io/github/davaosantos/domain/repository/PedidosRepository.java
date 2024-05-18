package io.github.davaosantos.domain.repository;

import io.github.davaosantos.domain.entity.Cliente;
import io.github.davaosantos.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {
    Set<Pedido> findByCliente(Cliente cliente);

}
