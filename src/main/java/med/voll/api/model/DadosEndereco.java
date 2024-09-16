package med.voll.api.model;

public record DadosEndereco(String logradouro,
                            String bairro,
                            String cep,
                            String cidade,
                            String uf,
                            String numero,
                            String complemento) {
}
