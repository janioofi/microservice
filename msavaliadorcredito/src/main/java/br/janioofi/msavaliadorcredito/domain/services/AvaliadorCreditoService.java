package br.janioofi.msavaliadorcredito.domain.services;

import br.janioofi.msavaliadorcredito.domain.entities.CartaoCliente;
import br.janioofi.msavaliadorcredito.domain.entities.DadosCliente;
import br.janioofi.msavaliadorcredito.domain.entities.SituacaoCliente;
import br.janioofi.msavaliadorcredito.domain.exceptions.BusinessException;
import br.janioofi.msavaliadorcredito.domain.exceptions.RecordNotFoundException;
import br.janioofi.msavaliadorcredito.domain.infra.clients.CartaoResourceClients;
import br.janioofi.msavaliadorcredito.domain.infra.clients.ClienteResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    private final ClienteResourceClient clienteResourceClient;
    private final CartaoResourceClients cartaoResourceClients;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws RecordNotFoundException{
        try {
            DadosCliente dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            List<CartaoCliente> cartaoCliente = cartaoResourceClients.getCartoesByCpf(cpf);
            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse)
                    .cartoes(cartaoCliente)
                    .build();
        }catch (FeignException.FeignClientException e){
            e.status();
            if(HttpStatus.NOT_FOUND.value() == e.status()){
                throw new RecordNotFoundException("Nenhum cliente encontrado com cpf " + cpf);
            }
            throw new BusinessException(e.getMessage(), e.status());
        }
    }
}
