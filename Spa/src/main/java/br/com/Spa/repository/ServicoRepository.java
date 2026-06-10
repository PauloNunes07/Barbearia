package br.com.Spa.repository;

import br.com.Spa.model.Agendamento;
import br.com.Spa.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("SELECT s from Servico s WHERE s.nome LIKE %:nome%")
    List<Servico> filtrarPorNome(@Param("nome") String nome);

    @Query("SELECT s from Servico s WHERE s.nome LIKE :nome")
    Optional<Servico> findByNome(@Param("nome") String nome);



}
