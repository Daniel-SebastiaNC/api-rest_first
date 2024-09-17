package med.voll.api.service;

import med.voll.api.model.DadosCadastroMedico;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private MedicoRepository repository;


    public void cadastrar(DadosCadastroMedico json) {
        repository.save(new Medico(json));
    }
}
