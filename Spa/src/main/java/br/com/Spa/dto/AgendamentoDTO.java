package br.com.Spa.dto;


import br.com.Spa.model.Cliente;
import br.com.Spa.model.Servico;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {

    @NotNull(message = "Id do Cliente obrigatório")
    @Positive(message = "ID do cliente deve ser maior que zero")
    private Long clienteID;

    @NotNull(message = "ID do Servico obrigatório")
    @Positive(message = "ID do serviço deve ser maior que zero")
    private Long servicoID;

    @NotNull(message = "Data e hora obrigatória")
    @Future(message = "O Agendamento deve ser para uma data e hora futura")
    private LocalDateTime dataHora;

    @Size(min = 5, max = 100, message = "A observação deve ter entre 5 e 100 caracteres")
    private String observacao;

}
