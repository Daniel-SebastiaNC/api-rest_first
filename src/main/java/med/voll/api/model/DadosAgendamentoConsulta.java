package med.voll.api.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade) {
}
