package br.com.Spa.repository;

import br.com.Spa.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
