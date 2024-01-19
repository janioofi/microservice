package br.janioofi.msclientes.domain.services;

import br.janioofi.msclientes.domain.DTO.ClienteDTO;
import br.janioofi.msclientes.domain.entities.Cliente;
import br.janioofi.msclientes.domain.exceptions.RecordNotFoundException;
import br.janioofi.msclientes.domain.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    private ModelMapper mapper = new ModelMapper().registerModule(new RecordModule());


    @Transactional
    public Cliente save(ClienteDTO data){
        Cliente cliente = mapper.map(data, Cliente.class);
        return repository.save(cliente);
    }

    public Cliente findByCpf(String cpf){
        return repository.findByCpf(cpf).orElseThrow(() -> {
            return new RecordNotFoundException("Nenhum cliente encontrado com esse CPF: " + cpf);
        });
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

}
