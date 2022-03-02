package br.com.alura.forum.persistence.requests;

import br.com.alura.forum.infra.profiles.Profiles;
import br.com.alura.forum.persistence.models.Usuario;
import br.com.alura.forum.persistence.repositories.PerfilRepository;
import br.com.alura.forum.persistence.validator.UniqueEmailValidation;
import br.com.alura.forum.infra.util.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegisterRequest {
    @NotEmpty @NotNull
    private String nome;

    @NotEmpty @NotNull
    private String senha;

    @UniqueEmailValidation
    @Email
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario generate(PerfilRepository perfilRepository){
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(PasswordEncoder.getEncoder().encode(senha));
        usuario.getPerfis().add(perfilRepository.findByNome(Profiles.ROLE_STUDENT.toString()).get());
        return usuario;
    }

}
