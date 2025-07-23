package med.voll.api.domain.paciente;

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
