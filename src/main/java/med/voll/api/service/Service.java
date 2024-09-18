package med.voll.api.service;

import jakarta.validation.Valid;
import med.voll.api.model.*;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private MedicoRepository repository;


    public ResponseEntity cadastrar(DadosCadastroMedico json, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(json);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalahmentoMedico(medico));
    }

    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable)
                .map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    public ResponseEntity atualizar(@Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarDados(dados);

        return ResponseEntity.ok(new DadosDetalahmentoMedico(medico));
    }

    public ResponseEntity excluir(Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity detalhar(Long id) {
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalahmentoMedico(medico));
    }
}
