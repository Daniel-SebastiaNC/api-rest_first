package med.voll.api.service;

import med.voll.api.model.DadosCadastroMedico;
import med.voll.api.model.DadosListagemMedico;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private MedicoRepository repository;


    public void cadastrar(DadosCadastroMedico json) {
        repository.save(new Medico(json));
    }

    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return repository.findAll(pageable)
                .map(DadosListagemMedico::new);
    }
}
