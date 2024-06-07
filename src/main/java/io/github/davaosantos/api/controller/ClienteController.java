package io.github.davaosantos.api.controller;


import io.github.davaosantos.domain.entity.Cliente;
import io.github.davaosantos.domain.repository.ClientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable("id") Integer idCliente){
        Optional<Cliente> cliente = clientesRepository.findById(idCliente);

        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    };

    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity saveCliente(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clientesRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    };

    @DeleteMapping("/deletar/{id}")
    @ResponseBody
    public ResponseEntity deletar(@PathVariable("id") Integer idCliente){
        Optional<Cliente> cliente = clientesRepository.findById(idCliente);

        if (cliente.isPresent()){
            clientesRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
