package br.com.Spa.controller;

import br.com.Spa.dto.ServicoDTO;
import br.com.Spa.model.Servico;
import br.com.Spa.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        return ResponseEntity.ok(servicoService.listar());
    }

    @GetMapping("/filtrar/{nome}")
    public ResponseEntity<?> filtrarPorNome(@PathVariable String nome) {

        List<Servico> servicos = servicoService.filtrarPorNome(nome);

        if (!servicos.isEmpty()) {
            return ResponseEntity.ok(servicos);
        }

        return ResponseEntity.status(404).body("Error! Nome não encontrado!");
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(servicoService.buscarPorId(id));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarServico(
            @RequestBody @Valid ServicoDTO servicoDTO) {

        try {
            servicoService.cadastrarServico(servicoDTO);
            return ResponseEntity.ok("Serviço cadastrado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerServico(@PathVariable Long id) {

        try {
            servicoService.removerServico(id);
            return ResponseEntity.ok("Serviço removido com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarServico(
            @PathVariable Long id,
            @RequestParam int duracao,
            @RequestParam double precoServico) {

        try {
            Servico servicoAtualizado =
                    servicoService.atualizarServico(id, duracao, precoServico);

            return ResponseEntity.ok(servicoAtualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}