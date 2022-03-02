package br.com.alura.forum.persistence.repositories;

import br.com.alura.forum.persistence.models.Topico;
import br.com.alura.forum.persistence.models.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    public void buscarUsuarioPorEmail(){
        String email = "aluno@email.com";
        Usuario user = repository.findByEmail(email).get();
        Assert.notNull(user, "Not null");
    }
}