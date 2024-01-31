package br.janioofi.mscartoes.controllers;

import br.janioofi.mscartoes.domain.dtos.CartaoDTO;
import br.janioofi.mscartoes.domain.dtos.ClienteCartaoDTO;
import br.janioofi.mscartoes.domain.entities.Cartao;
import br.janioofi.mscartoes.domain.services.CartaoService;
import br.janioofi.mscartoes.domain.services.ClienteCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartoes")
@RequiredArgsConstructor
public class CartoesController {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping("/status")
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Cartao> cadastra(@RequestBody CartaoDTO data){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoService.save(data));
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam Long renda){
        return ResponseEntity.ok().body(cartaoService.getCartoesRendaMenorIgual(   renda));
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClienteCartaoDTO>> getCartoesByCpf(@RequestParam String cpf){
        return ResponseEntity.ok().body(clienteCartaoService.listaCartoesByCpf(cpf));
    }


}
