package br.janioofi.mscartoes.domain.services;

import br.janioofi.mscartoes.domain.dtos.ClienteCartaoDTO;
import br.janioofi.mscartoes.domain.entities.ClienteCartao;
import br.janioofi.mscartoes.domain.repositories.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {
    private final ClienteCartaoRepository repository;

    public List<ClienteCartaoDTO> listaCartoesByCpf(String cpf){
        return repository.findByCpf(cpf).stream().map(ClienteCartaoDTO::fromModel).toList();
    }
}
