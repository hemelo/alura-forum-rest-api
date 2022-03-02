package br.com.alura.forum.persistence.requests;

import br.com.alura.forum.persistence.models.Resposta;
import br.com.alura.forum.persistence.models.Topico;
import br.com.alura.forum.persistence.repositories.TopicoRepository;
import javax.validation.constraints.Size;

public class RespostaRequest {
    private Long topicId;


    @Size(min=30)
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Resposta generate(TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getById(topicId);
        Resposta resposta = new Resposta();
        resposta.setMensagem(mensagem);
        resposta.setTopico(topico);
        return resposta;
    }

    public Resposta update(Resposta resposta) {
        resposta.setMensagem(mensagem);
        return resposta;
    }
}
