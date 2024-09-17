package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.DadosAtualizacaoMedico;
import med.voll.api.model.DadosCadastroMedico;
import med.voll.api.model.DadosListagemMedico;
import med.voll.api.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private Service service;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico json){
        service.cadastrar(json);
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        service.atualizar(dados);
    }
}
