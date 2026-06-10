package br.com.Spa.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDTO {

    @NotBlank(message = "Nome do serviço obrigatório")
    @Size(min = 4, max = 22)
    private String nome;

    @Min(value = 5, message = "Duração mínima de 5 minutos")
    @Max(value = 60, message = "Duração máxima de 60 minutos")
    private int duracao;

    @Positive(message = "Valor deve ser maior que zero")
    private double precoServico;

    @NotBlank(message = "Tipo de serviço é obrigatório")
    @Size(max = 10, message = "Tipo de serviço deve ter no máximo 10 caracteres")
    private String tipoServico;

    @NotBlank(message = "Descrição do serviço obrigatória")
    @Size(min = 10, max = 100, message =  "Descrição do serviço deve ter entre 10 e 50 caracteres")
    private String descricaoServico;
}
