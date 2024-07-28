package io.github.davaosantos.domain.repository;

import io.github.davaosantos.domain.entity.Cliente;
import io.github.davaosantos.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {
    Set<Pedido> findByCliente(Cliente cliente);

    @Query("select p from Pedido p left join fetch p.itemPedidos where p.id = :id")
    Optional<Pedido> findByIdFetchItemPedidos(@Param("id") Integer id);

}
