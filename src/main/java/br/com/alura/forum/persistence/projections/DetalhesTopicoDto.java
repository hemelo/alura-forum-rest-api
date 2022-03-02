package br.com.alura.forum.persistence.projections;

import br.com.alura.forum.persistence.models.StatusTopico;
import br.com.alura.forum.persistence.models.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesTopicoDto {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private String nomeAutor;
    private List<RespostaDto> respostas;

    public DetalhesTopicoDto(Topico topico){
        this.dataCriacao = topico.getDataCriacao();
        this.id = topico.getId();
        this.mensagem = topico.getMensagem();
        this.titulo = topico.getTitulo();
        this.nomeAutor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
    }

    public static List<DetalhesTopicoDto> converter(List<Topico> topicos){
        return topicos.stream().map(DetalhesTopicoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }
}
