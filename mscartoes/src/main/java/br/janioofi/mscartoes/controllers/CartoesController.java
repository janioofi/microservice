package br.janioofi.mscartoes.controllers;

import br.janioofi.mscartoes.domain.dtos.CartaoDTO;
import br.janioofi.mscartoes.domain.entities.Cartao;
import br.janioofi.mscartoes.domain.services.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartoes")
@RequiredArgsConstructor
public class CartoesController {

    private final CartaoService service;

    @GetMapping("/status")
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Cartao> cadastra(@RequestBody CartaoDTO data){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(data));
    }

}
