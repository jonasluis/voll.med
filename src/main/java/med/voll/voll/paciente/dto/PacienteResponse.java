package med.voll.voll.paciente.dto;


import med.voll.voll.endereco.Endereco;
import med.voll.voll.paciente.model.PacienteEntity;

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
