package br.com.Spa.service;

import br.com.Spa.dto.ClienteDTO;
import br.com.Spa.model.Cliente;
import br.com.Spa.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {

       return clienteRepository.findById(id).orElseThrow(() ->
               new RuntimeException("Cliente não encontrado"));

    }

    public Cliente cadastrarCliente(ClienteDTO clienteDTO){
        boolean clienteExiste = clienteRepository.findByNome(clienteDTO.getNome()).isPresent();

        if(clienteExiste){
            throw new RuntimeException("Já existe um Cliente com esse nome " + clienteDTO.getNome());
        }

        return clienteRepository.save(toEntity(clienteDTO));

    }

    public void removerCliente(Long id) {

       Cliente cliente = buscarPorId(id);
       clienteRepository.delete(cliente);
    }


    public List<Cliente> filtrarPorNome(String nome){
        return clienteRepository.filtrarPorNome(nome);
    }

    public Cliente atualizarContato(Long id, String telefone, String email){
        Cliente inforCliente = buscarPorId(id);

        inforCliente.setTelefone(telefone);
        inforCliente.setEmail(email);

       return  clienteRepository.save(inforCliente);

    }

    private Cliente toEntity(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());

        return cliente;
    }


}
