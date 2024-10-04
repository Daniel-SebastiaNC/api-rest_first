package med.voll.api.validacoes;

import med.voll.api.errs.ValidacaoException;
import med.voll.api.model.DadosAgendamentoConsulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentosConsultas{

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var medicoConsultaDisponivel = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoConsultaDisponivel){
            throw new ValidacaoException("Medico já está ocupado nesse horário");
        }
    }
}
