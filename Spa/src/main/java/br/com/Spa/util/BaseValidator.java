package br.com.Spa.util;

import java.time.LocalDateTime;

public abstract class BaseValidator {
    protected void validarDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new RuntimeException("Data e hora são obrigatórias");
        }
    }

    protected void validarPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        validarDataHora(inicio);
        validarDataHora(fim);
        if (inicio.isAfter(fim)) {
            throw new RuntimeException("A data de início não pode ser posterior à data de fim");

        }
    }

    protected void validarEntidade(Long id, String entidade) {
        if(id == null) {
            throw new RuntimeException("ID do " + entidade + " é obrigátorio");
        }
    }
}
