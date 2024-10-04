package med.voll.api.service;

import med.voll.api.errs.ValidacaoException;
import med.voll.api.model.*;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.validacoes.ValidadorAgendamentosConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorAgendamentosConsultas> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.stream().forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);

        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nesse horário!");
        }

        var consulta = new Consulta(null, medico, dados.data());

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados){
        if(dados.idMedico() != null){
           return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
