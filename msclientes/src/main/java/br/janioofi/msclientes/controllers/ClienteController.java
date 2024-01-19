package br.janioofi.msclientes.controllers;

import br.janioofi.msclientes.domain.DTO.ClienteDTO;
import br.janioofi.msclientes.domain.entities.Cliente;
import br.janioofi.msclientes.domain.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody ClienteDTO data){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cpf}").buildAndExpand(data.cpf()).toUri();
        return ResponseEntity.created(uri).body(service.save(data));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> findByCPF(@PathVariable String cpf){
        return ResponseEntity.ok().body(service.findByCpf(cpf));
    }
}