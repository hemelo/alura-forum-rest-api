package br.com.alura.forum.persistence.requests;

import br.com.alura.forum.persistence.models.Topico;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PatchTopicRequest {
    @NotNull @NotEmpty
    private String titulo;

    @NotNull @NotEmpty
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico update(Topico topico){
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        return topico;
    }

}
