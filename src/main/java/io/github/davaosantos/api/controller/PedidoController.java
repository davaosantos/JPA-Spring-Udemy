package io.github.davaosantos.api.controller;


import io.github.davaosantos.api.dto.InformacaoItemPedidoDTO;
import io.github.davaosantos.api.dto.InformacoesPedidoDTO;
import io.github.davaosantos.api.dto.ItemPedidoDTO;
import io.github.davaosantos.api.dto.PedidoDTO;
import io.github.davaosantos.domain.entity.ItemPedido;
import io.github.davaosantos.domain.entity.Pedido;
import io.github.davaosantos.service.PedidoService;
import io.github.davaosantos.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/ola")
    public String testarDevTools() {
        return "Ola devtools";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer salvarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable("id") Integer idPedido) {
        return pedidoService.obterPedidoCompleto(idPedido)
                .map(Utils::converterPedido)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

}
