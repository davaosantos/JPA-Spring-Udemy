package io.github.davaosantos.domain.repository;

import io.github.davaosantos.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
