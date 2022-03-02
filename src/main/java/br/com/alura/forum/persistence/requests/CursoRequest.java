package br.com.alura.forum.persistence.requests;

import br.com.alura.forum.persistence.models.Curso;
import br.com.alura.forum.persistence.models.Topico;
import br.com.alura.forum.persistence.validator.UniqueCursoValidation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CursoRequest {
    @NotNull @NotEmpty @UniqueCursoValidation
    private String nome;

    @NotNull @NotEmpty
    private String categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Curso update(Curso curso) {
        curso.setCategoria(categoria);
        curso.setNome(nome);
        return curso;
    }

    public Curso generate() {
        Curso curso = new Curso();
        curso.setCategoria(categoria);
        curso.setNome(nome);
        return curso;
    }
}
