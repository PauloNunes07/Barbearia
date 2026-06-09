package br.com.Spa.controller;

import br.com.Spa.model.Cliente;
import br.com.Spa.model.Servico;
import br.com.Spa.repository.ServicoRepository;

import br.com.Spa.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public List<Servico> listarServicos() {
        return servicoService.listar();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarServico(@RequestBody Servico servico) {

        try {
            servicoService.cadastrarServico(servico);
            return ResponseEntity.status(201).body("Serviço Cadastrado Com Sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }

    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerCliente(@PathVariable Long id) {
        try {
            servicoService.removerServico(id);
            return ResponseEntity.status(200).body("Serviço Removido Com Sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarServico(
            @PathVariable Long id,
            @RequestParam int duracao,
            @RequestParam double precoServico) {

        Optional<Servico> resultado =
                servicoService.atualizarServico(id, duracao, precoServico);

        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        }

        return ResponseEntity.status(404)
                .body("Servico não encontrado!");
    }
}
