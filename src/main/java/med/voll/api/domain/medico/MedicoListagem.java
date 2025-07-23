package med.voll.api.domain.medico;

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
