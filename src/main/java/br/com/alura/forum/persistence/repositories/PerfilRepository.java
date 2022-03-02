package br.com.alura.forum.persistence.repositories;

import br.com.alura.forum.persistence.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByNome(String toString);
}
