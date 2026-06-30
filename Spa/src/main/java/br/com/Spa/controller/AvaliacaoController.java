package br.com.Spa.controller;

import br.com.Spa.dto.AvaliacaoDTO;
import br.com.Spa.model.Avaliacao;
import br.com.Spa.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoService.listar());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarAvaliacao(
            @RequestBody @Valid AvaliacaoDTO avaliacaoDTO) {

        try {
            avaliacaoService.cadastrarAvaliacao(avaliacaoDTO);
            return ResponseEntity.ok("Avaliação cadastrada com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(avaliacaoService.buscarPorId(id));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<?> listarPorCliente(@PathVariable Long idCliente) {

        try {
            return ResponseEntity.ok(avaliacaoService.listarPorCliente(idCliente));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/agendamento/{idAgendamento}")
    public ResponseEntity<?> buscarPorAgendamento(@PathVariable Long idAgendamento) {

        try {
            return ResponseEntity.ok(avaliacaoService.buscaPorAgendamento(idAgendamento));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/nota/{nota}")
    public ResponseEntity<?> buscarPorNota(@PathVariable int nota) {

        try {
            return ResponseEntity.ok(avaliacaoService.buscarPorNota(nota));

        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/notaMinima/{nota}")
    public ResponseEntity<?> filtrarPorNotaMinima(@PathVariable int nota) {

        try {
            return ResponseEntity.ok(avaliacaoService.filtrarPorNotaMinima(nota));

        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerAvaliacao(@PathVariable Long id) {

        try {
            avaliacaoService.removerAvaliacao(id);
            return ResponseEntity.ok("Avaliação removida com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}