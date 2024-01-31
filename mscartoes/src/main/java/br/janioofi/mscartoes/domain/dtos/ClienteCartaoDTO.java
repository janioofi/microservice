package br.janioofi.mscartoes.domain.dtos;

import br.janioofi.mscartoes.domain.entities.ClienteCartao;

import java.math.BigDecimal;

public record ClienteCartaoDTO(
        String nome,
        String bandeira,
        BigDecimal limite
) {
    public static ClienteCartaoDTO fromModel(ClienteCartao data){
        return new ClienteCartaoDTO(
                data.getCartao().getNome(),
                data.getCartao().getBandeira().toString(),
                data.getLimite());
    }
}
