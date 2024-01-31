package br.janioofi.msavaliadorcredito.domain.services;

import br.janioofi.msavaliadorcredito.domain.entities.DadosCliente;
import br.janioofi.msavaliadorcredito.domain.entities.SituacaoCliente;
import br.janioofi.msavaliadorcredito.domain.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    private final ClienteResourceClient clienteResourceClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        // Obter dados do cliente - MSCLIENTES
        // Obter cartões do cliente - MSCARTOES
        DadosCliente dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse)
                .build();
    }
}
