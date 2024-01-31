package br.janioofi.msavaliadorcredito.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosCliente {
    private Long id;
    private String cpf;
    private String nome;
    private Integer idade;
}
