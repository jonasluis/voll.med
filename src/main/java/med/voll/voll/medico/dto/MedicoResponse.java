package med.voll.voll.medico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.voll.medico.model.MedicoEntity;
import med.voll.voll.medico.model.enums.Especialidade;

public record MedicoResponse(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {

    public  MedicoResponse(MedicoEntity medicoEntity){
        this(medicoEntity.getNome(), medicoEntity.getEmail(), medicoEntity.getCrm(), medicoEntity.getEspecialidade());

    }
}
