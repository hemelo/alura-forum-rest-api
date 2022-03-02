package br.com.alura.forum.persistence.repositories;

import br.com.alura.forum.persistence.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>, JpaSpecificationExecutor<Topico>, PagingAndSortingRepository<Topico, Long> {
}
