package io.github.davaosantos.service;


import io.github.davaosantos.model.Cliente;
import io.github.davaosantos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        this.clienteRepository.persistir(cliente);
    };

    public void validarCliente(Cliente cliente){
        //aplica validações
    }

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
}
