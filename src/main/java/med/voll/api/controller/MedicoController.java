package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.model.DadosAtualizacaoMedico;
import med.voll.api.model.DadosCadastroMedico;
import med.voll.api.model.DadosListagemMedico;
import med.voll.api.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private Service service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico json, UriComponentsBuilder uriBuilder){
        return service.cadastrar(json, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable pageable) {
        return service.listar(pageable);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        return service.atualizar(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        return service.excluir(id);

    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity detalhar(@PathVariable Long id){
        return service.detalhar(id);

    }
}
