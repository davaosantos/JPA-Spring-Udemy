package io.github.davaosantos.domain.repository;

import io.github.davaosantos.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
}
