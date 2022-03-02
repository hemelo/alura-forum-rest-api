package br.com.alura.forum.persistence.repositories;

import br.com.alura.forum.persistence.models.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long>{
}
