package br.janioofi.mscartoes.domain.entities;

import br.janioofi.mscartoes.domain.dtos.CartaoDTO;
import br.janioofi.mscartoes.domain.enums.BandeiraCartao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cartao {
    @Id
    @Column(name = "id_cartao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCartao;
    private String nome;
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }
}
