package med.voll.voll.paciente.dto;

import med.voll.voll.medico.model.MedicoEntity;
import med.voll.voll.medico.model.enums.Especialidade;
import med.voll.voll.paciente.model.PacienteEntity;

public record PacienteListagem(
        Long id,
        String nome,
        String email,
        String cpf
) {

    public PacienteListagem(PacienteEntity pacienteEntity){
        this(pacienteEntity.getId(), pacienteEntity.getNome(), pacienteEntity.getEmail(), pacienteEntity.getCpf());
    }
}
