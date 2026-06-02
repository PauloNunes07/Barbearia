package br.com.Spa.controller;

import br.com.Spa.model.Cliente;
import br.com.Spa.repository.ClienteRepository;

import br.com.Spa.service.ClienteService;
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
    public ResponseEntity<List<Cliente>> getClientes() {
        return ResponseEntity.ok();
    }

    @PostMapping("/cadastrar")
    public void cadastrarCliente(@RequestBody Cliente cliente) {
         clienteRepository.save(cliente);
    }

    @DeleteMapping("/remover/{id}")
    public void removerCliente(@PathVariable Long id) {
        if(clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        }
    }

}
