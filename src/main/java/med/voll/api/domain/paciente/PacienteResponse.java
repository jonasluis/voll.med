package med.voll.api.domain.paciente;


import med.voll.api.domain.endereco.Endereco;

public record PacienteResponse(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public PacienteResponse(PacienteEntity pacienteEntity) {
        this(
        pacienteEntity.getId(),
        pacienteEntity.getNome(),
        pacienteEntity.getEmail(),
        pacienteEntity.getTelefone(),
        pacienteEntity.getCpf(),
        pacienteEntity.getEndereco()
        );

    }

}
