package br.janioofi.msavaliadorcredito.domain.infra.clients;

import br.janioofi.msavaliadorcredito.domain.entities.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/api/cartoes", name = "mscartoes")
public interface CartaoResourceClients {

    @GetMapping(params = "cpf")
    List<CartaoCliente> getCartoesByCpf(@RequestParam String cpf);
}
