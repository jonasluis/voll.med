package med.voll.voll.medico.model;

import jakarta.persistence.*;
import lombok.*;

import med.voll.voll.endereco.Endereco;
import med.voll.voll.medico.dto.MedicoRequest;
import med.voll.voll.medico.model.enums.Especialidade;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "medicos")
@EqualsAndHashCode(of = "id")
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Integer crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public MedicoEntity(MedicoRequest medicoRequest) {
        this.nome = medicoRequest.nome();
        this.email = medicoRequest.email();
        this.crm = medicoRequest.crm();
        this.especialidade = medicoRequest.especialidade();
        this.endereco = new Endereco(medicoRequest.endereco());
    }
}
