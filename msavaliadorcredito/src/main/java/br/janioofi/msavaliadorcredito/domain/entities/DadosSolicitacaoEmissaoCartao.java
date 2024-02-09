package br.janioofi.msavaliadorcredito.domain.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaoCartao {

    private Long id_cartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
