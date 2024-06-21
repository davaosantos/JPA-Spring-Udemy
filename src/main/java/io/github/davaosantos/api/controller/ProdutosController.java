package io.github.davaosantos.api.controller;


import io.github.davaosantos.domain.entity.Produto;
import io.github.davaosantos.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    ProdutosRepository produtosRepository;

    @GetMapping
    public List<Produto> buscaProdutos(Produto produto){
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Produto> produtoFilter = Example.of(produto);

        return produtosRepository.findAll(produtoFilter);
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.OK)
    public Produto salvarProduto(@RequestBody Produto produto){
        return produtosRepository.save(produto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto produto(@PathVariable("id") Integer idProduto){
        return produtosRepository.findById(idProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto(@PathVariable("id") Integer idProduto, @RequestBody Produto produto){
        produtosRepository.findById(idProduto)
                .map( p -> {
                    produtosRepository.save(p);
                    return p;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable("id") Integer idProduto){
        produtosRepository.findById(idProduto)
                .map( p -> {
                    produtosRepository.delete(p);
                    return p;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

}
