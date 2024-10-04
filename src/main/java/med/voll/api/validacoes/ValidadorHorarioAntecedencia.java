package med.voll.api.validacoes;

import med.voll.api.errs.ValidacaoException;
import med.voll.api.model.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentosConsultas {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var agora = LocalDateTime.now();

        var diferenca = Duration.between(agora, dataConsulta).toMinutes();

        if (diferenca <30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
