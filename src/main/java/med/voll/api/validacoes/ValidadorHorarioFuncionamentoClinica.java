package med.voll.api.validacoes;

import med.voll.api.errs.ValidacaoException;
import med.voll.api.model.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentosConsultas {


    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAbertura = dataConsulta.getHour() < 7;
        var depoisFechamento = dataConsulta.getHour() > 18;

        if(domingo || antesAbertura || depoisFechamento) {
            throw new ValidacaoException("Consulta fora do horário");
        }
    }
}
