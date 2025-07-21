package med.voll.voll.endereco;

import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotNull
         String logradouro,
         String numero,
         String complemento,
         String bairro,
         String cidade,
         String uf,
         String cep
) {
}
