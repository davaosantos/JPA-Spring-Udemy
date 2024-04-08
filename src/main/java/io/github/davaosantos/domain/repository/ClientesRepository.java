package io.github.davaosantos.domain.repository;


import io.github.davaosantos.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientesRepository {

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";

    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ? ";

    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ? ";

    private static String SELECT_BY_NAME = "SELECT * FROM CLIENTE WHERE NOME LIKE ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cliente> buscaClientesPorNome(String nmPesquisa){
        return jdbcTemplate.query(SELECT_BY_NAME,
                new Object[]{"%" + nmPesquisa + "%"},
                getRowMapper());
    };

    private static RowMapper<Cliente> getRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Cliente(resultSet.getInt("id"), resultSet.getString("nome"));
            }
        };
    }

    public void deletar(Cliente cliente){
        deletarClientePorId(cliente.getId());
    }

    private void deletarClientePorId(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    };

    public Cliente salvarCliente(Cliente cliente){
        jdbcTemplate.update(INSERT , new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> obterTodos(){
       return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }

}
