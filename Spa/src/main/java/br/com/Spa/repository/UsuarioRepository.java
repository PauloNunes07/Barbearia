package br.com.Spa.repository;

import br.com.Spa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String nome);

    boolean existsByLogin(String nome);
}
