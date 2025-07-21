package med.voll.voll.endereco.model;

public record Endereco(
         String logadouro,
         String numero,
         String complemento,
         String bairro,
         String cidade,
         String uf,
         String cep
) {
}
