package med.voll.voll.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.voll.endereco.EnderecoDTO;
import med.voll.voll.medico.model.enums.Especialidade;

public record MedicoCadastro(
        
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
       
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        EnderecoDTO endereco
) {
}
