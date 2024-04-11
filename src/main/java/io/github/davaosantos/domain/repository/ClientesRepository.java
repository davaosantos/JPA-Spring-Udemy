package io.github.davaosantos.domain.repository;


import io.github.davaosantos.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ClientesRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Cliente> buscaClientesPorNome(String nmPesquisa){
        String jpql = " select c from Cliente c where c.nome like :nome ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nmPesquisa + "%");
        return query.getResultList();
    };

    @Transactional
    public void deletarClientePorId(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }

    @Transactional
    public void deletar(Cliente cliente){

        if (!entityManager.contains(cliente)){ // EntityManager não está sincronizado com esse cliente
            entityManager.merge(cliente); //Sincronizo
        }
        entityManager.remove(cliente);
    }



    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    };

    @Transactional
    public Cliente salvarCliente(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public List<Cliente> obterTodos(){
       return entityManager
               .createQuery(" from Cliente ", Cliente.class).getResultList();
    }

}
