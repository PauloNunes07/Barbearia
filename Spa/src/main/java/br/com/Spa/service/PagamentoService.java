package br.com.Spa.service;

import br.com.Spa.dto.PagamentoDTO;
import br.com.Spa.model.Agendamento;
import br.com.Spa.util.BaseValidator;
import br.com.Spa.model.Pagamento;
import br.com.Spa.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService extends BaseValidator {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private AgendamentoService agendamentoService;

    public List<Pagamento> listar() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPorId(Integer id) {
        return pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public Pagamento cadastrarPagamento(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = toEntity(pagamentoDTO);
        Agendamento agendamento = pagamento.getAgendamento();
        if(pagamentoRepository.findByAgendamentoID(agendamento.getId()).isPresent()) {
            throw new RuntimeException("Já existe um pagamento para esse agendamento");
        }
        return pagamentoRepository.save(pagamento);

    }
    public Pagamento buscarPorDataPagamento(LocalDateTime dataHoraPagamento) {
        validarDataHora(dataHoraPagamento);
        return pagamentoRepository.buscarPorDataHora(dataHoraPagamento).orElseThrow(() -> new RuntimeException("Não existe pagamento nesse horário"));

    }

    public List<Pagamento> filtrarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
       validarPeriodo(inicio, fim);
       return pagamentoRepository.filtrarPorData(inicio, fim);
    }

    public Optional<Pagamento> buscarPorAgendamento(Long idAgendamento) {
        validarEntidade(idAgendamento, "Agendamento");
        agendamentoService.buscarPorId(idAgendamento);
        return pagamentoRepository.findByAgendamentoID(idAgendamento);
    }

    private Pagamento toEntity(PagamentoDTO pagamentoDTO) {
        Agendamento agendamento = agendamentoService.buscarPorId(pagamentoDTO.getAgendamento().getId());
        Pagamento pagamento = new Pagamento();
        pagamento.setAgendamento(agendamento);
        pagamento.setFormaPagamento(pagamentoDTO.getFormaPagamento());
        pagamento.setPreco(agendamento.getPrecoTotal());
        pagamento.setDataPagamento(LocalDateTime.now());

        return pagamento;
    }
}
