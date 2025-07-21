package med.voll.voll.medico.dto;

import med.voll.voll.endereco.EnderecoDTO;
import med.voll.voll.medico.model.enums.Especialidade;

public record MedicoRequest(
        String nome,
        String email,
        Integer crm,
        Especialidade especialidade,
        EnderecoDTO endereco
) {
}
