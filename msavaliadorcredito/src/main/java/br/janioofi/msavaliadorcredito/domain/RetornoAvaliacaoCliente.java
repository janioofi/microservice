package br.janioofi.msavaliadorcredito.domain;

import br.janioofi.msavaliadorcredito.domain.entities.CartaoAprovado;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
    private List<CartaoAprovado> cartoes;
}
