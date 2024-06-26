package io.github.davaosantos.service.imp;


import io.github.davaosantos.domain.repository.PedidosRepository;
import io.github.davaosantos.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidosRepository pedidosRepository;

    public PedidoServiceImpl(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }

}
