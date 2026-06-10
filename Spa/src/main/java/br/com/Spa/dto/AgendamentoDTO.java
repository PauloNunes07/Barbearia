package br.com.Spa.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {

    @NotNull(message = "ID do cliente obrigatório")
    @Positive(message = "ID do cliente deve ser maior que zero")
    private Long clienteId;

    @NotNull(message = "ID do servico obrigatório")
    @Positive(message = "ID do servico deve ser maior que zero")
    private Long servicoId;

    @NotNull(message = "Data e hora obrigatória")
    @Future(message = "O Agendamento deve ser para uma data hora futura")
    private LocalDateTime dataHora;

    @NotBlank(message = "Observação obrigatória")
    @Size(
            min = 5,
            max = 100,
            message = "A observação deve ter entre 5 e 100 caracteres"
    )
    private String observacao;

}
