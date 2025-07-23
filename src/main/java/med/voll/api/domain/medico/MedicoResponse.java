package med.voll.api.domain.medico;


import med.voll.api.domain.endereco.Endereco;

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
