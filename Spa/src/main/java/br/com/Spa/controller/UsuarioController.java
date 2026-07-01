package br.com.Spa.controller;

import br.com.Spa.dto.ClienteDTO;
import br.com.Spa.dto.UsuarioDTO;
import br.com.Spa.model.Usuario;
import br.com.Spa.repository.UsuarioRepository;
import br.com.Spa.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listar();
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<?> buscarPorID(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(usuarioService.buscarPorId(id));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(
            @RequestBody @Valid UsuarioDTO usuarioDTO) {

        try {
            usuarioService.cadastrar(usuarioDTO);
            return ResponseEntity.ok("Usuario cadastrado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> removerAvaliacao(@PathVariable Long id) {

        try {
            usuarioService.remover(id);
            return ResponseEntity.ok("Usuario removido com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("alterar/{id}")
    public ResponseEntity<?> atualizarSenha(
            @PathVariable Long id,
            @RequestParam String senhaAtual,
            @RequestParam String novaSenha) {

        try {
            return ResponseEntity.ok(
                    usuarioService.alterarSenha(id, senhaAtual, novaSenha)
            );

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }
}
