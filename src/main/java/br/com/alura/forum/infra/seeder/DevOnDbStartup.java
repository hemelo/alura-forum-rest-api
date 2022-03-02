package br.com.alura.forum.infra.seeder;

import br.com.alura.forum.infra.profiles.Profiles;
import br.com.alura.forum.infra.util.PasswordEncoder;
import br.com.alura.forum.persistence.models.Perfil;
import br.com.alura.forum.persistence.models.Usuario;
import br.com.alura.forum.persistence.repositories.PerfilRepository;
import br.com.alura.forum.persistence.repositories.UsuarioRepository;
import br.com.alura.forum.persistence.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Profile("dev")
@Component
public class DevOnDbStartup implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        Arrays.asList(Profiles.values())
            .forEach(p -> {
                if(perfilRepository.findByNome(p.toString()).isEmpty()) {
                    Perfil perfil = new Perfil();
                    perfil.setNome(p.toString());
                    perfilRepository.save(perfil);
                }
            });

        if(usuarioRepository.findByEmail("temporary@admin.com").isEmpty()){
            Usuario usuario = new Usuario();
            usuario.setEmail("temporary@admin.com");
            usuario.setNome("Administrator");
            usuario.setSenha(PasswordEncoder.getEncoder().encode("123456"));
            usuario.getPerfis().add(perfilRepository.findByNome(Profiles.ROLE_ADMIN.toString()).get());
            usuarioRepository.save(usuario);
        }
    }
}
