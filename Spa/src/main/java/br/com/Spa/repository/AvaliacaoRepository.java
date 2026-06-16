package br.com.Spa.repository;

import br.com.Spa.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    @Query("SELECT a FROM Avaliacao a WHERE a.agendamento.id = :idAgendamento")
    Optional<Avaliacao> findByAgendamento(@Param("idAgendamento") Long idAgendamento);

    @Query("SELECT a FROM Avaliacao a WHERE a.cliente.id = :idCliente")
    List<Avaliacao> findByCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT a FROM Avaliacao a WHERE a.notaAvaliacao = :nota")
    List<Avaliacao> buscarPorNota(@Param("nota") int nota);

    @Query("SELECT a FROM Avaliacao a WHERE a.notaAvaliacao >= :nota")
    List<Avaliacao> buscarPorNotaMinima(@Param("nota") int nota);


}
