package br.com.Spa.repository;

import br.com.Spa.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {


    @Query("SELECT p FROM Pagamento p WHERE p.agendamento.id = :idAgendamento")
    Optional<Pagamento> findByAgendamentoID(@Param("idAgendamento") Long idAgendamento);

    @Query("SELECT p FROM Pagamento p WHERE p.dataPagamento BETWEEN :inicio AND :fim")
    List<Pagamento> filtrarPorData(@Param("inicio") LocalDateTime inicio,
                                     @Param("fim") LocalDateTime fim);

    @Query("SELECT p FROM Pagamento p WHERE p.dataPagamento = :dataHora")
    Optional<Pagamento> buscarPorDataHora(@Param("dataHora") LocalDateTime dataHora);
}
