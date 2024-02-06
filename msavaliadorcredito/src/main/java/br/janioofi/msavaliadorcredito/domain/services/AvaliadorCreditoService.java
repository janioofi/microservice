package br.janioofi.msavaliadorcredito.domain.services;

import br.janioofi.msavaliadorcredito.domain.RetornoAvaliacaoCliente;
import br.janioofi.msavaliadorcredito.domain.entities.*;
import br.janioofi.msavaliadorcredito.domain.exceptions.BusinessException;
import br.janioofi.msavaliadorcredito.domain.exceptions.RecordNotFoundException;
import br.janioofi.msavaliadorcredito.domain.infra.clients.CartaoResourceClients;
import br.janioofi.msavaliadorcredito.domain.infra.clients.ClienteResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda){
        try{
            DadosCliente dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            List<Cartao> cartoesResponse = cartaoResourceClients.getCartoesRendaAte(renda);

            var listaCartoesAprovados = cartoesResponse.stream().map(cartao -> {
                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosClienteResponse.getIdade());
                BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);
                return aprovado;
            }).collect(Collectors.toList());
            return new RetornoAvaliacaoCliente(listaCartoesAprovados);

        }catch (FeignException.FeignClientException e){
            e.status();
            if(HttpStatus.NOT_FOUND.value() == e.status()){
                throw new RecordNotFoundException("Nenhum cliente encontrado com cpf " + cpf);
            }
            throw new BusinessException(e.getMessage(), e.status());
        }
    }
}
