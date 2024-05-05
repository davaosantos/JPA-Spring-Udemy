package io.github.davaosantos.domain.repository;


import io.github.davaosantos.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = " select c from Cliente c where c.nome like :nome ")
    List<Cliente> encontrarPorNomeJpql(@Param("nome") String nome);

    @Query(value = " select * from cliente ce where ce.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNomeNativo(@Param("nome") String nome);

    boolean existsByNome(String nome);

    @Modifying
    @Query(" delete from Cliente c where c.nome  =:nome ")
    void deleteByNome(@Param("nome") String nome);

}
