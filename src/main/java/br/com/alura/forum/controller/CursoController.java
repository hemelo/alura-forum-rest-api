package br.com.alura.forum.controller;

import br.com.alura.forum.persistence.models.Curso;
import br.com.alura.forum.persistence.projections.CursoDto;
import br.com.alura.forum.persistence.repositories.CursoRepository;
import br.com.alura.forum.persistence.requests.CursoRequest;
import br.com.alura.forum.persistence.specification.builders.SpecificationBuilder;
import br.com.alura.forum.infra.util.QueryPattern;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

@RequestMapping("/cursos")
@RestController
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @Operation(summary = "Get all cursos")
    @GetMapping
    @Cacheable("listaDeCursos")
    public Page<CursoDto> lista(
            @Parameter(example = "id>20;titulo:teste") @RequestParam(value = "search", required = false) String search,
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 25) Pageable paginacao
    ) {
        SpecificationBuilder<Curso> builder = new SpecificationBuilder<>();
        Matcher matcher = QueryPattern.getMatcher(search);

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Curso> spec = builder.build();

        Page<Curso> cursos = cursoRepository.findAll(spec, paginacao);

        return CursoDto.converter(cursos);
    }

    @Operation(summary = "Get curso by ID")
    @GetMapping("{id}")
    public CursoDto curso(@PathVariable Long id) {
        Curso curso = cursoRepository.getById(id);
        return new CursoDto(curso);
    }


    @Operation(summary = "Add curso", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeCursos", allEntries = true)
    public ResponseEntity<CursoDto> cadastrar(@RequestBody @Valid CursoRequest cursoRequest, UriComponentsBuilder uriBuilder) {
        Curso curso = cursoRequest.generate();
        cursoRepository.save(curso);
        URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursoDto(curso));
    }

    @Operation(summary = "Update curso", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("{id}")
    @Transactional
    @CacheEvict(value = "listaDeCursos", allEntries = true)
    public ResponseEntity<CursoDto> atualizar(@PathVariable Long id, @RequestBody @Valid CursoRequest cursoRequest) {
        Curso curso = cursoRepository.getById(id);
        curso = cursoRequest.update(curso);
        cursoRepository.save(curso);
        return ResponseEntity.ok(new CursoDto(curso));
    }

    @Operation(summary = "Remove curso", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("{id}")
    @Transactional
    @CacheEvict(value = "listaDeCursos", allEntries = true)
    public ResponseEntity remover(@PathVariable Long id) {
        cursoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
