package br.com.alura.forum.persistence.repositories;

import br.com.alura.forum.persistence.models.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Test
    public void buscarCursoPorNome(){
        String nomeCurso = "HTML 5";
        Curso curso = repository.findByNome(nomeCurso);
        Assert.notNull(curso, "Not null");
    }
}