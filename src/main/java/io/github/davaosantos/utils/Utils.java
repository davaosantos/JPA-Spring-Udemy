package io.github.davaosantos.utils;

import io.github.davaosantos.api.dto.InformacaoItemPedidoDTO;
import io.github.davaosantos.api.dto.InformacoesPedidoDTO;
import io.github.davaosantos.domain.entity.ItemPedido;
import io.github.davaosantos.domain.entity.Pedido;
import org.springframework.util.CollectionUtils;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static InformacoesPedidoDTO converterPedido(Pedido pedido) {
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDtPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converterItemPedido(pedido.getItemPedidos()))
                .build();
    }

    public static List<InformacaoItemPedidoDTO> converterItemPedido(List<ItemPedido> itemPedidos) {
        if (CollectionUtils.isEmpty(itemPedidos)) {
            return Collections.emptyList();
        }

        return itemPedidos.stream().map(
                itemPedido -> InformacaoItemPedidoDTO.builder()
                        .descricaoProduto(itemPedido.getProduto().getDescricao())
                        .precoUnitario(itemPedido.getProduto().getPreco())
                        .quantidade(itemPedido.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }

}
