package br.com.Spa.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {

    @NotNull(message = "ID do Cliente é obrigatório")
    @Positive(message = "ID do cliente deve ser maior que zero")
    private Long clienteId;

    @NotNull(message = "ID do Agendamento é obrigatório")
    @Positive(message = "ID do Agendamento deve ser maior que zero")
    private Long agendamentoId;

    @NotNull(message = "Nota da avaliação obrigatória")
    @Min(value = 1, message = "Nota minima da avaliação deve ser 1")
    @Max(value = 5, message = "Nota maxima da avaliacão deve ser 5")
    private int notaAvaliacao;

    @Size(max = 100, message = "Comentario deve ter no maximo 100 caracteres")
    private String comentario;

}
