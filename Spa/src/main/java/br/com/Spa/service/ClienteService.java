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

    public Cliente cadastrarCliente(Cliente cliente){
        Cliente clienteExistente = clienteRepository.buscarPorNome(cliente.getNome());
        if(clienteExistente != null){
            throw new RuntimeException("Já existe um Cliente com esse nome " + cliente.getNome());
        }
        return clienteRepository.save(cliente);

    }


    public boolean removerCliente(Long id) {
        if(clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("O Id que deseja remover não foi encontrado ");
    }


    public List<Cliente> filtarPorNome(String nome){
        return clienteRepository.filtrarPorNome(nome);
    }

    public Optional<Cliente> atualizarContato(Long id, String telefone, String email){
        Cliente inforCliente = clienteRepository.buscarPorId(id);
        if(inforCliente != null){
            inforCliente.setTelefone(telefone);
            inforCliente.setEmail(email);

            return  Optional.of(clienteRepository.save(inforCliente));
        }

        return Optional.empty();

    }


}
