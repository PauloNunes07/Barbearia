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
    public ResponseEntity<?> filtrarPorNome(@PathVariable String nome){

        List<Cliente> clientes = clienteService.filtrarPorNome(nome);
        if(!clientes.isEmpty()) {
            return ResponseEntity.ok(clientes);
        }

        return ResponseEntity.status(404).body("Error! Nome não encontrado !");

    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> buscarPorID(@PathVariable Long id){
        try {
            Cliente cliente = clienteService.buscarPorId(id);
            return ResponseEntity.ok(cliente);

        } catch(RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {

        try{
            clienteService.cadastrarCliente(clienteDTO);
            return ResponseEntity.ok("Cliente Cadastrado Com Sucesso!");

        } catch (RuntimeException e){
            return ResponseEntity.status(409).body(e.getMessage());
        }

    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerCliente(@PathVariable Long id) {
        try{
            clienteService.removerCliente(id);
            return ResponseEntity.ok("Cliente Removido Com Sucesso!");

        }
        catch (RuntimeException e){
            return ResponseEntity.status(409).body(e.getMessage());
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarContato(
            @PathVariable Long id,
            @RequestParam String telefone,
            @RequestParam String email) {

        try {
            Cliente clienteAtualizado = clienteService.atualizarContato(id, telefone, email);
            return ResponseEntity.ok(clienteAtualizado);

        } catch (RuntimeException erro){
            return ResponseEntity.status(404).body(erro.getMessage());
        }
    }



}
