package br.com.Spa.controller;

import br.com.Spa.dto.ServicoDTO;
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

    @GetMapping("/buscar/{nome}")
    public ResponseEntity<?> filtrarPorNome(@PathVariable String nome){

        List<Servico> servicos = servicoService.filtrarPorNome(nome);
        if(!servicos.isEmpty()) {
            return ResponseEntity.ok(servicos);
        }

        return ResponseEntity.status(404).body("Error! Nome não encontrado !");

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarServico(@RequestBody ServicoDTO servicoDTO) {

        try {
            servicoService.cadastrarServico(servicoDTO);
            return ResponseEntity.ok("Serviço Cadastrado Com Sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }

    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerServico(@PathVariable Long id) {
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

        try {
            Servico ServicoAtualizado = servicoService.atualizarServico(id, duracao, precoServico);
            return ResponseEntity.ok(ServicoAtualizado);

        }catch (RuntimeException erro) {
            return ResponseEntity.status(404).body("Servico não encontrado!");
        }

    }
}
