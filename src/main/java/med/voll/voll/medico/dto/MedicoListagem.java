package med.voll.voll.medico.dto;

import med.voll.voll.medico.model.MedicoEntity;
import med.voll.voll.medico.model.enums.Especialidade;

public record MedicoListagem(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {

    public MedicoListagem(MedicoEntity medicoEntity){
        this(medicoEntity.getId(), medicoEntity.getNome(), medicoEntity.getEmail(), medicoEntity.getCrm(), medicoEntity.getEspecialidade());

    }
}
