package br.com.Spa.service;

import br.com.Spa.dto.AvaliacaoDTO;
import br.com.Spa.model.Agendamento;
import br.com.Spa.model.Avaliacao;
import br.com.Spa.model.Cliente;
import br.com.Spa.repository.AvaliacaoRepository;
import br.com.Spa.util.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService extends BaseValidator {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ClienteService clienteService;

    public List<Avaliacao> listar() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao cadastrarAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        boolean agendamentoExiste = buscaPorAgendamento(avaliacaoDTO.getAgendamentoId()).isPresent();
        if(agendamentoExiste) {
            throw new RuntimeException("Já existe uma avaliação para esse agendamento ");

        }
        return avaliacaoRepository.save(toEntity(avaliacaoDTO));

    }
    public Avaliacao buscarPorId(Long id) {
        return avaliacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("id de Avaliação não encontrado"));

    }    public List<Avaliacao> listarPorCliente(Long idCliente) {
        validarEntidade(idCliente, "Cliente");
        return avaliacaoRepository.findByCliente(idCliente);
    }

    public Optional<Avaliacao> buscaPorAgendamento(Long idAgendamento) {
        validarEntidade(idAgendamento, "Agendamento");
        return avaliacaoRepository.findByAgendamento(idAgendamento);
    }

    public List<Avaliacao> buscarPorNota(int nota) {
        return avaliacaoRepository.buscarPorNota(nota);
    }

    public List<Avaliacao> filtrarPorNotaMinima(int nota) {
        return avaliacaoRepository.buscarPorNotaMinima(nota);
    }

    public void removerAvaliacao(Long id) {
        Avaliacao avaliacao = buscarPorId(id);
        avaliacaoRepository.delete(avaliacao);
    }

    private Avaliacao toEntity(AvaliacaoDTO avaliacaoDTO) {
        Cliente buscaCliente =  clienteService.buscarPorId(avaliacaoDTO.getClienteId());
        Agendamento agendamento = agendamentoService.buscarPorId(avaliacaoDTO.getAgendamentoId());

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setCliente(buscaCliente);
        avaliacao.setAgendamento(agendamento);
        avaliacao.setNotaAvaliacao(avaliacaoDTO.getNotaAvaliacao());
        avaliacao.setComentario(avaliacaoDTO.getComentario());

        return avaliacao;

    }



}
