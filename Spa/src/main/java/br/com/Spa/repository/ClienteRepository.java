package br.com.Spa.repository;

import br.com.Spa.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    @Query("SELECT c from Cliente c WHERE c.nome LIKE %:nome%")
    List<Cliente> filtrarPorNome(@Param("nome")String nome);

    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
    Optional<Cliente> findByNome(@Param("nome") String nome);

}
