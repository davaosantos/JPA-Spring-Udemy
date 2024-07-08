package io.github.davaosantos.service.imp;


import io.github.davaosantos.api.dto.ItemPedidoDTO;
import io.github.davaosantos.api.dto.PedidoDTO;
import io.github.davaosantos.domain.entity.Cliente;
import io.github.davaosantos.domain.entity.ItemPedido;
import io.github.davaosantos.domain.entity.Pedido;
import io.github.davaosantos.domain.entity.Produto;
import io.github.davaosantos.domain.repository.ClientesRepository;
import io.github.davaosantos.domain.repository.ItemsPedidoRepository;
import io.github.davaosantos.domain.repository.PedidosRepository;
import io.github.davaosantos.domain.repository.ProdutosRepository;
import io.github.davaosantos.exception.RegraNegocioException;
import io.github.davaosantos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;

    private final ClientesRepository clientesRepository;

    private final ProdutosRepository produtosRepository;

    private final ItemsPedidoRepository itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {

        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDtPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = converterItems(pedido, pedidoDTO.getItems());

        pedidosRepository.save(pedido);

        //pedido.setTotal(recalculaTotalStream(itemPedidos));

        itemsPedidoRepository.saveAll(itemPedidos);

        pedido.setItemPedidos(itemPedidos);

        return pedido;
    }

//    private BigDecimal recalculaTotalStream(List<ItemPedido> itemPedidos) {
//        return itemPedidos.stream()
//                .map(ItemPedido::getProduto)
//                .map(Produto::getPreco)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itens){
        if (itens.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar o pedido sem itens");
        }

        return itens.stream()
                .map(itemPedidoDTO -> {
                    Integer idProduto = itemPedidoDTO.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Produto não encontrado , ID : " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                    return itemPedido;
                }).collect(Collectors.toList());

    }

}
