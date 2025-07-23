package med.voll.voll.paciente.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.voll.endereco.EnderecoDTO;

public record PacienteUpdate(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
