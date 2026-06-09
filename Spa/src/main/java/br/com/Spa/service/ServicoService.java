package br.com.Spa.service;

import br.com.Spa.model.Cliente;
import br.com.Spa.model.Servico;
import br.com.Spa.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listar() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(Long id) {

        if(servicoRepository.existsById(id)){
            return servicoRepository.findById(id);
        }

        throw new RuntimeException("erro esse id não existe");

    }

    public Servico cadastrarServico(Servico servico){
        Servico servicoExistente = servicoRepository.buscarPorNome(servico.getNome());
        if(servicoExistente != null){
            throw new RuntimeException("Já existe um Cliente com esse nome " + servico.getNome());
        }
        return servicoRepository.save(servico);

    }

    public boolean removerServico(Long id) {
        if(servicoRepository.existsById(id)) {
            servicoRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("O Id que deseja remover não foi encontrado ");
    }

    public List<Servico> filtarPorNome(String nome){
        return servicoRepository.filtrarPorNome(nome);
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
