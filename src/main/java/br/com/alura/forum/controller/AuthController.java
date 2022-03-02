package br.com.alura.forum.controller;

import br.com.alura.forum.persistence.models.Usuario;
import br.com.alura.forum.persistence.repositories.PerfilRepository;
import br.com.alura.forum.persistence.repositories.UsuarioRepository;
import br.com.alura.forum.persistence.requests.RegisterRequest;
import br.com.alura.forum.service.TokenService;
import br.com.alura.forum.persistence.projections.TokenDto;
import br.com.alura.forum.persistence.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Profile(value = {"prod", "test"})
public class AuthController {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.generate();
        Authentication auth = authenticationManager.authenticate(dadosLogin);
        String token = tokenService.generate(auth);
        return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    }

    @PostMapping("register")
    public ResponseEntity<TokenDto> register(@RequestBody @Valid RegisterRequest registerRequest){
        Usuario usuario = registerRequest.generate(perfilRepository);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}
