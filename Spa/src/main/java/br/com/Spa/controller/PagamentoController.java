package br.com.Spa.controller;

import br.com.Spa.dto.PagamentoDTO;
import br.com.Spa.model.Pagamento;
import br.com.Spa.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarPagamentos() {
        return ResponseEntity.ok(pagamentoService.listar());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {

        try {
            return ResponseEntity.ok(pagamentoService.buscarPorId(id));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarPagamento(
            @RequestBody @Valid PagamentoDTO pagamentoDTO) {

        try {
            pagamentoService.cadastrarPagamento(pagamentoDTO);
            return ResponseEntity.ok("Pagamento cadastrado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/buscarPorDataPagamento")
    public ResponseEntity<?> buscarPorDataPagamento(
            @RequestParam LocalDateTime dataHoraPagamento) {

        try {
            return ResponseEntity.ok(
                    pagamentoService.buscarPorDataPagamento(dataHoraPagamento)
            );

        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/filtrar/periodo")
    public ResponseEntity<?> filtrarPorPeriodo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {

        try {
            return ResponseEntity.ok(
                    pagamentoService.filtrarPorPeriodo(inicio, fim)
            );

        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/agendamento/{idAgendamento}")
    public ResponseEntity<?> buscarPorAgendamento(
            @PathVariable Long idAgendamento) {

        try {
            return ResponseEntity.ok(
                    pagamentoService.buscarPorAgendamento(idAgendamento)
            );

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}