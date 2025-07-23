package med.voll.voll.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.voll.endereco.EnderecoDTO;

public record MedicoUpdate(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
