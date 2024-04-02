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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvarCliente(Cliente cliente){
        jdbcTemplate.update(INSERT , new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> obterTodos(){
       return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {

                String nome = resultSet.getString("nome");
                Integer idCliente = resultSet.getInt("id");

                return new Cliente(idCliente, nome);
            }
        });
    };
}
