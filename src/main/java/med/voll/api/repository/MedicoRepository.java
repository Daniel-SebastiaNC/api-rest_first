package med.voll.api.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);
/*
    @Query("""
                select m from Medico m
                where
                m.ativo = 1
                and
                m.especialidade = :especialidade
                and
                m.id not in(
                        select c.medico.id from Consulta c
                        where
                        c.data = :data
                )
                order by rand()
                limit 1
                """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);*/

    @Query("SELECT m FROM Medico m " +
            "WHERE m.especialidade = :especialidade " +
            "AND NOT EXISTS ( " +
            "SELECT 1 FROM Consulta c " +
            "WHERE c.medico = m " +
            "AND c.data = :data " +
            ") " +
            "ORDER BY RANDOM()")
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, @NotNull @Future LocalDateTime data);

    @Query("""
           SELECT m.ativo
           FROM Medico m
           WHERE
           m.id = :id
           """)
    Boolean findAtivoById(Long id);
}
