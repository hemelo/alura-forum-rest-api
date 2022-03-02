package br.com.alura.forum.persistence.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Documented
@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCursoValidator.class)
public @interface UniqueCursoValidation {
    public String message() default "Curso already registered";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
