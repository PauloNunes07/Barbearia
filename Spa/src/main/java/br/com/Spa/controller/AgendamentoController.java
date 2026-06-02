package br.com.Spa.controller;

import br.com.Spa.model.Agendamento;
import br.com.Spa.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping
    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    @PostMapping("/adicionar")
    public void adicionarAgendamento(@RequestBody Agendamento agendamento) {
        agendamentoRepository.save(agendamento);
    }

    @DeleteMapping("/remover/{id}")
    public void removerAgendamento(@PathVariable Long id) {
        if(agendamentoRepository.existsById(id)) {
            agendamentoRepository.deleteById(id);
        }
    }


}
