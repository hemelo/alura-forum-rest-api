package br.com.alura.forum.persistence.validator;

import br.com.alura.forum.persistence.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCursoValidator  implements ConstraintValidator<UniqueCursoValidation, String> {
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public boolean isValid(String nome, ConstraintValidatorContext constraintValidatorContext) {
        return cursoRepository.findByNome(nome) == null;
    }
}
