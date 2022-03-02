package br.com.alura.forum.persistence.projections;

import br.com.alura.forum.persistence.models.Curso;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class CursoDto {
    private Long id;
    private String nome;
    private String categoria;

    public CursoDto(Curso curso){
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.categoria = curso.getCategoria();
    }

    public static List<CursoDto> converter(List<Curso> cursos){
        return cursos.stream().map(CursoDto::new).collect(Collectors.toList());
    }

    public static Page<CursoDto> converter(Page<Curso>cursos){
        return cursos.map(CursoDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
