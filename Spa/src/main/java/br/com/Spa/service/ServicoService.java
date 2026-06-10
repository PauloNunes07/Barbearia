package br.com.Spa.service;

import br.com.Spa.dto.ServicoDTO;
import br.com.Spa.model.Servico;
import br.com.Spa.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listar() {
        return servicoRepository.findAll();
    }

    public Servico buscarPorId(Long id) {
        return servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

    }

    public Servico cadastrarServico(ServicoDTO servicoDTO){
        boolean servicoExiste = servicoRepository.findByNome(servicoDTO.getNome()).isPresent();
       if(servicoExiste){
           throw new RuntimeException("Já existe um Serviço com esse nome " + servicoDTO.getNome());

       }
       return servicoRepository.save(toEntity(servicoDTO));

    }

    public void removerServico(Long id) {
      Servico servico = buscarPorId(id);
      servicoRepository.delete(servico);
    }

    public List<Servico> filtrarPorNome(String nome){
        return servicoRepository.filtrarPorNome(nome);
    }

    public Servico atualizarServico(Long id, int duracao, double precoServico){
        Servico inforServico = buscarPorId(id);
        inforServico.setDuracao(duracao);
        inforServico.setPrecoServico(precoServico);

        return servicoRepository.save(inforServico);

    }

    private Servico toEntity(ServicoDTO servicoDTO){
        Servico servico = new Servico();
        servico.setNome(servicoDTO.getNome());
        servico.setDuracao(servicoDTO.getDuracao());
        servico.setPrecoServico(servicoDTO.getPrecoServico());
        servico.setTipoServico(servicoDTO.getTipoServico());
        servico.setDescricaoServico(servicoDTO.getDescricaoServico());

        return servico;
    }

}
