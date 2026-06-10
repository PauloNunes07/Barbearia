package br.com.Spa.dto;

import br.com.Spa.enuns.FormaPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {

    @NotNull(message = "ID do Agendamento Obrigatório")
    @Positive(message = "O ID do agedamento deve ser maior que 0")
    private Long agendamentoId;

    @NotNull(message = "Forma de pagamento obrigatório")
    private FormaPagamento formaPagamento;



}
