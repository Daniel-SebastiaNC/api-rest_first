package med.voll.api.validacoes;

import med.voll.api.errs.ValidacaoException;
import med.voll.api.model.DadosAgendamentoConsulta;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentosConsultas{

    @Autowired
    private MedicoRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var medidoAtivo = repository.findAtivoById(dados.idMedico());
        if (!medidoAtivo) {
            throw new ValidacaoException("Não pode ser agendado");
        }
    }
}
