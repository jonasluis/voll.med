package med.voll.voll.medico.dto;

import med.voll.voll.endereco.model.Endereco;
import med.voll.voll.medico.model.enums.Especialidade;

public record MedicoRequest(
        String nome,
        String email,
        Integer telefone,
        Integer crm,
        Especialidade especialidade,
        Endereco enderecoCompleto
) {
}
