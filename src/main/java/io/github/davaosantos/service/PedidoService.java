package io.github.davaosantos.service;

import io.github.davaosantos.api.dto.PedidoDTO;
import io.github.davaosantos.domain.entity.Pedido;
import io.github.davaosantos.domain.enums.StatusPedido;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
