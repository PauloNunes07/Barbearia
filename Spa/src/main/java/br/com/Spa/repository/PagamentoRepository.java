package br.com.Spa.repository;

import br.com.Spa.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
