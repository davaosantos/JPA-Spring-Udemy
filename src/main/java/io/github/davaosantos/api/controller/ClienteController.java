package io.github.davaosantos.api.controller;


import io.github.davaosantos.domain.entity.Cliente;
import io.github.davaosantos.domain.repository.ClientesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable("id") Integer idCliente){
        return clientesRepository.findById(idCliente).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")
        );
    };

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@RequestBody Cliente cliente){
        return clientesRepository.save(cliente);
    };

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") Integer idCliente){
        clientesRepository.findById(idCliente)
                .map(cliente -> {
                    clientesRepository.delete(cliente);
                    return cliente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente atualizar(
            @PathVariable("id") Integer idCliente,
            @RequestBody Cliente cliente){

        return clientesRepository
                .findById(idCliente)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientesRepository.save(cliente);
                    return clienteExistente;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping
    @ResponseBody
    public List<Cliente> buscar(Cliente filtro){

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Cliente> example = Example.of(filtro, matcher );

        return clientesRepository.findAll(example);

    }

}
