package br.com.Spa.controller;

import br.com.Spa.dto.LoginRequestDTO;
import br.com.Spa.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {

        String token = authService.login(dto.getLogin(), dto.getSenha());

        return ResponseEntity.ok(
                Map.of("token", token)
        );
    }
}