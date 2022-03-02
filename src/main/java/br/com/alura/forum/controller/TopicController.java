package br.com.alura.forum.controller;

import br.com.alura.forum.persistence.models.Usuario;
import br.com.alura.forum.persistence.projections.DetalhesTopicoDto;
import br.com.alura.forum.persistence.projections.TopicoDto;
import br.com.alura.forum.persistence.repositories.CursoRepository;
import br.com.alura.forum.persistence.requests.PatchTopicRequest;
import br.com.alura.forum.persistence.requests.TopicRequest;
import br.com.alura.forum.persistence.specification.builders.SpecificationBuilder;
import br.com.alura.forum.persistence.models.Topico;
import br.com.alura.forum.persistence.repositories.TopicoRepository;
import br.com.alura.forum.service.AuthService;
import br.com.alura.forum.infra.util.QueryPattern;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.regex.Matcher;

@RequestMapping("/topicos")
@RestController
public class TopicController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Operation(summary = "Get all topics")
    @GetMapping
    public Page<TopicoDto> lista(
            @Parameter(example = "id>20;titulo:teste") @RequestParam(value = "search", required = false) String search,
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 25) Pageable paginacao
    ) {
        SpecificationBuilder<Topico> builder = new SpecificationBuilder<>();
        Matcher matcher = QueryPattern.getMatcher(search);

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Topico> spec = builder.build();

        Page<Topico> topicos = topicoRepository.findAll(spec, paginacao);

        return TopicoDto.converter(topicos);
    }

    @Operation(summary = "Get detailed topic by ID")
    @GetMapping("{id}")
    public DetalhesTopicoDto topico(@PathVariable Long id) {
        Topico topico = topicoRepository.getById(id);
        return new DetalhesTopicoDto(topico);
    }

    @Operation(summary = "Add topic", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicRequest topicoRequest, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoRequest.generate(cursoRepository);
        topico.setAutor((Usuario) authService.getUserByContext());
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @Operation(summary = "Update topic", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid PatchTopicRequest topicoRequest) {
        Topico topico = topicoRepository.getById(id);
        topico = topicoRequest.update(topico);
        topicoRepository.save(topico);
        return ResponseEntity.ok(new TopicoDto(topico));
    }

    @Operation(summary = "Remove topic", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
