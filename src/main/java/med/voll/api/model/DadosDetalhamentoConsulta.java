package med.voll.api.model;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getData());
    }
}
