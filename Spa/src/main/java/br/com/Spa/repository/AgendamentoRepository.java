package br.com.Spa.repository;

import br.com.Spa.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
