package br.com.Spa.repository;

import br.com.Spa.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("SELECT a FROM Agendamento a WHERE a.cliente.id = :idCliente")
    List<Agendamento> findByCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT a FROM Agendamento a WHERE a.servico.id = :idServico")
    List<Agendamento> findByServico(@Param("idServico") Long idServico);

    @Query("SELECT a FROM Agendamento a WHERE a.data BETWEEN :inicio AND :fim")
    List<Agendamento> filtrarPorData(@Param("inicio") LocalDateTime inicio,
                                    @Param("fim") LocalDateTime fim);

    @Query("SELECT a FROM Agendamento a WHERE a.data = :dataHora")
    Optional<Agendamento> buscarPorDataHora(@Param("dataHora") LocalDateTime dataHora);

}
