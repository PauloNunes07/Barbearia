package br.com.Spa.service;

import br.com.Spa.dto.AgendamentoDTO;
import br.com.Spa.model.Agendamento;
import br.com.Spa.util.BaseValidator;
import br.com.Spa.model.Cliente;
import br.com.Spa.model.Servico;
import br.com.Spa.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService extends BaseValidator {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicoService servicoService;

    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

    }

    public void removerAgendamento(Long id) {
        Agendamento agendamento = buscarPorId(id);
        agendamentoRepository.delete(agendamento);
    }

    public Agendamento cadastrarAgendamento(AgendamentoDTO agendamentoDTO) {

        boolean agendamentoExiste = agendamentoRepository.buscarPorDataHora(agendamentoDTO.getDataHora()).isPresent();
        if (agendamentoExiste) {
            throw new RuntimeException("Já existe um agendamento com essa data e hora " + agendamentoDTO.getDataHora());

        }

        return agendamentoRepository.save(toEntity(agendamentoDTO));
    }

    public List<Agendamento> filtrarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        validarPeriodo(inicio, fim);
        return agendamentoRepository.filtrarPorData(inicio, fim);
    }

    public List<Agendamento> filtrarPorCliente(Long idCliente) {
        validarEntidade(idCliente, "Cliente");
        return agendamentoRepository.findByCliente(idCliente);
    }

    public List<Agendamento> filtrarPorServico(Long idServico) {
        validarEntidade(idServico, "Servico");
        return agendamentoRepository.findByServico(idServico);
    }

    public Agendamento atualizarAgendamento(Long id, LocalDateTime dataHora) {

        validarDataHora(dataHora);
        Agendamento infoAgendamento = buscarPorId(id);
        Optional<Agendamento> conflito = agendamentoRepository.buscarPorDataHora(dataHora);

        if (conflito.isPresent() && !conflito.get().getId().equals(id)) {
            throw new RuntimeException("Horário já ocupado para outro agendamento");
        }

        infoAgendamento.setDataHora(dataHora);
        return agendamentoRepository.save(infoAgendamento);
    }

    public Optional<Agendamento> buscarPorDataHora(LocalDateTime dataHora) {
        validarDataHora(dataHora);
        return agendamentoRepository.buscarPorDataHora(dataHora);
    }

    private Agendamento toEntity(AgendamentoDTO agendamentoDTO) {

        Cliente buscaCliente = clienteService.buscarPorId(agendamentoDTO.getCliente().getId());
        Servico buscaServico = servicoService.buscarPorId(agendamentoDTO.getServico().getId());

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(buscaCliente);
        agendamento.setServico(buscaServico);
        agendamento.setPrecoTotal(buscaServico.getPrecoServico());
        agendamento.setDataHora(agendamentoDTO.getDataHora());
        agendamento.setObservacao(agendamentoDTO.getObservacao());

        return agendamento;
    }







}
