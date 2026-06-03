package br.com.Spa.service;

import br.com.Spa.model.Cliente;
import br.com.Spa.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {

        if(clienteRepository.existsById(id)){
            return clienteRepository.findById(id);
        }

        throw new RuntimeException("erro esse id não existe");

    }

    public List<Cliente> buscarPorNome(String nome) {
        return buscarPorNome(nome);
    }

    public




}
