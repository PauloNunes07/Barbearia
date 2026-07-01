package br.com.Spa.dto;

import br.com.Spa.util.enuns.TipoUsuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {

    @NotNull(message = "Login Obrigatório")
    private String login;

    @NotNull(message = "senha obrigatória")
    private String senha;

    @NotNull(message = "Tipo de usuário obrigatório")
    private TipoUsuario tipoUsuario;


}
