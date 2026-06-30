package br.com.Spa.controller;

import br.com.Spa.dto.ClienteDTO;
import br.com.Spa.model.Cliente;
import br.com.Spa.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<?> filtrarPorNome(@PathVariable String nome) {

        try {
            return ResponseEntity.ok(clienteService.filtrarPorNome(nome));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> buscarPorID(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(clienteService.buscarPorId(id));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(
            @RequestBody @Valid ClienteDTO clienteDTO) {

        try {
            clienteService.cadastrarCliente(clienteDTO);
            return ResponseEntity.ok("Cliente cadastrado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerCliente(@PathVariable Long id) {

        try {
            clienteService.removerCliente(id);
            return ResponseEntity.ok("Cliente removido com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarContato(
            @PathVariable Long id,
            @RequestParam String telefone,
            @RequestParam String email) {

        try {
            return ResponseEntity.ok(
                    clienteService.atualizarContato(id, telefone, email)
            );

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}