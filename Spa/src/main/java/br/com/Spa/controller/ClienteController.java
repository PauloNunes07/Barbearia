package br.com.Spa.controller;

import br.com.Spa.model.Cliente;

import br.com.Spa.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/buscar/{nome}")
    public ResponseEntity<?> filtrarPorNome(@PathVariable String nome){

        List<Cliente> clientes = clienteService.filtarPorNome(nome);
        if(!clientes.isEmpty()){
            return ResponseEntity.status(200).body(clientes);

        }else{
            return ResponseEntity.status(404).body("Error! Nome não encontrado !");
        }

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        try{
            clienteService.cadastrarCliente(cliente);
            return ResponseEntity.status(201).body("Cliente Cadastrado Com Sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.status(409).body(e.getMessage());
        }

    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerCliente(@PathVariable Long id) {
        try{
            clienteService.removerCliente(id);
            return ResponseEntity.status(200).body("Cliente Removido Com Sucesso!");

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

        Optional<Cliente> resultado =
                clienteService.atualizarContato(id, telefone, email);

        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        }

        return ResponseEntity.status(404)
                .body("Cliente não encontrado!");
    }
}
