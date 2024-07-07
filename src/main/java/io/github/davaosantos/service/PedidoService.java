package io.github.davaosantos.service;

import io.github.davaosantos.api.dto.PedidoDTO;
import io.github.davaosantos.domain.entity.Pedido;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

}
