package br.janioofi.mscartoes.domain.dtos;

import br.janioofi.mscartoes.domain.enums.BandeiraCartao;

import java.math.BigDecimal;

public record CartaoDTO(
        String nome,
        BandeiraCartao bandeira,
        BigDecimal renda,
        BigDecimal limiteBasico
) {
}
