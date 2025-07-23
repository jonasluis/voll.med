package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        @NotBlank(message = "Logradouro é obrigatório")
        String logradouro,
        String numero,
        String complemento,
        @NotBlank(message = "Complemento é obrigatório")
        String bairro,
        @NotBlank(message = "Cidade é obrigatório")
        String cidade,
        @NotBlank(message = "UF é obrigatório")
        String uf,
        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{8}")
        String cep
) {
}
