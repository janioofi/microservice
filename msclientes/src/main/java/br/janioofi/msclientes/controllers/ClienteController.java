package br.janioofi.msclientes.controllers;

import br.janioofi.msclientes.domain.dtos.ClienteDTO;
import br.janioofi.msclientes.domain.entities.Cliente;
import br.janioofi.msclientes.domain.services.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        log.info("Microservice Clientes:  Listando todos os clientes");
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody ClienteDTO data){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cpf}").buildAndExpand(data.cpf()).toUri();
        return ResponseEntity.created(uri).body(service.save(data));
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Cliente> findByCPF(@RequestParam String cpf){
        return ResponseEntity.ok().body(service.findByCpf(cpf));
    }
}
