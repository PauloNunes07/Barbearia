package br.com.Spa.repository;

import br.com.Spa.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.id = :id")
    Servico buscarPorId(@Param("id")Long id);

    @Query("SELECT s from Servico s WHERE s.nome LIKE %:nome%")
    List<Servico> filtrarPorNome(@Param("nome") String nome);

    @Query("SELECT s from Servico s WHERE s.nome LIKE :nome")
    Servico buscarPorNome(@Param("nome") String nome);

}
