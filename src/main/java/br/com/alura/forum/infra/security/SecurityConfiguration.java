package br.com.alura.forum.infra.security;

import br.com.alura.forum.infra.interceptor.TokenVerifier;
import br.com.alura.forum.persistence.repositories.UsuarioRepository;
import br.com.alura.forum.service.AuthService;
import br.com.alura.forum.service.TokenService;
import br.com.alura.forum.infra.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Profile(value = {"prod", "test"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(authService);
        auth.setPasswordEncoder(PasswordEncoder.getEncoder());
        return auth;
    }

    @Override // Autorização
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .antMatchers(HttpMethod.GET, "/cursos").permitAll()
                .antMatchers(HttpMethod.GET, "/cursos/*").permitAll()
                .antMatchers(HttpMethod.POST, "/cursos/*").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/cursos/*").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/cursos/*").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/topicos/*").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/respostas/*").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.GET, "/actuator/**").hasAnyRole("MODERATOR", "ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new TokenVerifier(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

    }

    @Override // Recursos estáticos
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/swagger-ui/*", "/api-docs/**", "/v3/**", "/webjars/**", "/swagger-resources/**", "/configuration/**");
    }

    @Override //Autenticação
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
