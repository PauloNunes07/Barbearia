package br.com.Spa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "Login obrigatorio")
    private String login;

    @NotBlank(message = "Senha obrigatoria")
    private String senha;

}