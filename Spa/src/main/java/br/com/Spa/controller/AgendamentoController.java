package br.com.Spa.controller;

import br.com.Spa.dto.AgendamentoDTO;
import br.com.Spa.model.Agendamento;
import br.com.Spa.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        return ResponseEntity.ok(agendamentoService.listar());
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(agendamentoService.buscarPorId(id));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarAgendamento(
            @RequestBody @Valid AgendamentoDTO agendamentoDTO) {

        try {
            agendamentoService.cadastrarAgendamento(agendamentoDTO);
            return ResponseEntity.ok("Agendamento cadastrado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerAgendamento(@PathVariable Long id) {

        try {
            agendamentoService.removerAgendamento(id);
            return ResponseEntity.ok("Agendamento removido com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<?> filtrarPorCliente(@PathVariable Long idCliente) {

        try {
            return ResponseEntity.ok(
                    agendamentoService.filtrarPorCliente(idCliente));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/servico/{idServico}")
    public ResponseEntity<?> filtrarPorServico(@PathVariable Long idServico) {

        try {
            return ResponseEntity.ok(
                    agendamentoService.filtrarPorServico(idServico));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarAgendamento(
            @PathVariable Long id,
            @RequestParam LocalDateTime dataHora) {

        try {
            return ResponseEntity.ok(
                    agendamentoService.atualizarAgendamento(id, dataHora));

        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}