package br.janioofi.msavaliadorcredito.domain.infra.clients;

import br.janioofi.msavaliadorcredito.domain.entities.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(/*url = "http://localhost:8080", */path = "/api/clientes", name = "msclientes", value = "msclientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    DadosCliente dadosCliente(@RequestParam String cpf);
}
