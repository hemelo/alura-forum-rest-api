package br.com.alura.forum.persistence.projections;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

public class ValidationErrorDto {
    private String campo;
    private String mensagem;

    public ValidationErrorDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public ValidationErrorDto(FieldError fieldError, MessageSource translator){
        this.campo = fieldError.getField();
        this.mensagem = translator.getMessage(fieldError, LocaleContextHolder.getLocale());
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
