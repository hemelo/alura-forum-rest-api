package br.com.alura.forum.infra.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {

    @Bean
    public static BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
