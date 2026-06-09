package br.com.Spa.service;

import br.com.Spa.model.Cliente;
import br.com.Spa.model.Servico;
import br.com.Spa.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    public Optional<Servico> atualizarServico(Long id, int duracao, double preco){
        Servico inforServico = servicoRepository.buscarPorId(id);
        if(inforServico != null){
            inforServico.setDuracao(duracao);
            inforServico.setPrecoServico(preco);

            return  Optional.of(servicoRepository.save(inforServico));
        }

        return Optional.empty();

    }

}
