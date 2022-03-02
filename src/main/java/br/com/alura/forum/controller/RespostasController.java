package br.com.alura.forum.controller;

import br.com.alura.forum.persistence.models.Resposta;
import br.com.alura.forum.persistence.models.Topico;
import br.com.alura.forum.persistence.models.Usuario;
import br.com.alura.forum.persistence.projections.RespostaDto;
import br.com.alura.forum.persistence.repositories.RespostaRepository;
import br.com.alura.forum.persistence.repositories.TopicoRepository;
import br.com.alura.forum.persistence.requests.RespostaRequest;
import br.com.alura.forum.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/respostas")
@RestController
public class RespostasController {
    @Autowired
    private AuthService authService;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Operation(summary = "Add resposta to topic", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    @Transactional
    public ResponseEntity<RespostaDto> responder(@RequestBody @Valid RespostaRequest respostaRequest, UriComponentsBuilder uriBuilder) {
        Resposta resposta = respostaRequest.generate(topicoRepository);
        resposta.setAutor((Usuario) authService.getUserByContext());
        respostaRepository.save(resposta);
        URI uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new RespostaDto(resposta));
    }

    @Operation(summary = "Update resposta", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<RespostaDto> atualizar(@PathVariable Long id, @RequestBody @Valid RespostaRequest respostaRequest) {
        Resposta resposta = respostaRepository.getById(id);
        resposta = respostaRequest.update(resposta);
        respostaRepository.save(resposta);
        return ResponseEntity.ok(new RespostaDto(resposta));
    }

    @Operation(summary = "Remove resposta", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        respostaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
