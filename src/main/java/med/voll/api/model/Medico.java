package med.voll.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico json) {
        this.crm =json.crm();
        this.email = json.email();
        this.telefone = json.telefone();
        this.nome = json.nome();
        this.especialidade = json.especialidade();
        this.endereco = new Endereco(json.endereco());
        this.ativo = true;
    }

    public void atualizarDados(@Valid DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.endereco() != null) {
            this.endereco.atualizarDados(dados.endereco());
        }


    }

    public void excluir() {
        this.ativo = false;
    }
}
