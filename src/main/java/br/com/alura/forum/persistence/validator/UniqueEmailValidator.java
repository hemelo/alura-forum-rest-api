package br.com.alura.forum.persistence.validator;

import br.com.alura.forum.persistence.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailValidation, String> {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return usuarioRepository.findByEmail(email).isEmpty();
    }
}
