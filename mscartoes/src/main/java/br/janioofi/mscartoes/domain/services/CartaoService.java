package br.janioofi.mscartoes.domain.services;

import br.janioofi.mscartoes.domain.dtos.CartaoDTO;
import br.janioofi.mscartoes.domain.entities.Cartao;
import br.janioofi.mscartoes.domain.repositories.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {
    private final CartaoRepository repository;
    private final ModelMapper mapper = new ModelMapper().registerModule(new RecordModule());

    public List<Cartao> getCartoesRendaMenorIgual(Long renda){
        var rendBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendBigDecimal);
    }

    @Transactional
    public Cartao save(CartaoDTO data){
        Cartao cartao = mapper.map(data, Cartao.class);
        return repository.save(cartao);
    }
}
