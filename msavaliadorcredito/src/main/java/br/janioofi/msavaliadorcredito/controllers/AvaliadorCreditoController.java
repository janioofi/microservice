package br.janioofi.msavaliadorcredito.controllers;

import br.janioofi.msavaliadorcredito.domain.RetornoAvaliacaoCliente;
import br.janioofi.msavaliadorcredito.domain.entities.DadosAvaliacao;
import br.janioofi.msavaliadorcredito.domain.entities.DadosSolicitacaoEmissaoCartao;
import br.janioofi.msavaliadorcredito.domain.entities.ProtocoloSolicitacaoCartao;
import br.janioofi.msavaliadorcredito.domain.entities.SituacaoCliente;
import br.janioofi.msavaliadorcredito.domain.exceptions.ErrorSolicitacaoCartaoException;
import br.janioofi.msavaliadorcredito.domain.services.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/avaliador-credito")
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping(value = "/situacao-cliente", params = "cpf")
    public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(@RequestParam String cpf){
        return ResponseEntity.ok().body(avaliadorCreditoService.obterSituacaoCliente(cpf));
    }

    @PostMapping
    public ResponseEntity<RetornoAvaliacaoCliente> realizarAvaliacao(@RequestBody DadosAvaliacao dados){
        RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
        return ResponseEntity.ok(retornoAvaliacaoCliente);
    }

    @PostMapping("/solicitacoes-cartao")
    public ResponseEntity solicitaCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados){
        try{
            ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService.solicitarEmissaoDeCartao(dados);
            return ResponseEntity.ok(protocoloSolicitacaoCartao);
        }catch (ErrorSolicitacaoCartaoException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
