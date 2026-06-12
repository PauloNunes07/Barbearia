package br.com.Spa.dto;

import br.com.Spa.util.enuns.FormaPagamento;
import br.com.Spa.model.Agendamento;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {

    @NotNull(message = "ID do Agendamento Obrigatório")
    private Agendamento agendamento;

    @NotNull(message = "Forma de pagamento obrigatório")
    private FormaPagamento formaPagamento;



}
