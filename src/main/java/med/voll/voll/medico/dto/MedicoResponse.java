package med.voll.voll.medico.dto;


import med.voll.voll.endereco.Endereco;
import med.voll.voll.medico.model.MedicoEntity;
import med.voll.voll.medico.model.enums.Especialidade;

public record MedicoResponse(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
    public MedicoResponse(MedicoEntity medicoEntity) {
        this(
        medicoEntity.getId(),
        medicoEntity.getNome(),
        medicoEntity.getEmail(),
        medicoEntity.getTelefone(),
        medicoEntity.getCrm(),
        medicoEntity.getEspecialidade(),
        medicoEntity.getEndereco()
        );

    }

}
