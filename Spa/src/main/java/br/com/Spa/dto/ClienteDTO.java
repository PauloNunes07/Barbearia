package br.com.Spa.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteDTO {

    @NotBlank(message = "Nome obrigatório")
    @Size(min = 4, max = 100)
    private String nome;

    @Min(value = 15, message = "A idade minima é 15 anos")
    @Max(value = 100, message = "A idade maxima é 100 anos")
    private int idade;

    @NotBlank(message = "Telefone obrigatório")
    @Pattern(regexp = "\\d{11}",
    message = "o telefone deve conter exatamente 11 digitos numéricos")
    private String telefone;

    @Email(message = "Email inválido")
    private String email;
}
