package med.voll.api.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import med.voll.api.domain.endereco.Endereco;

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
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public MedicoEntity(MedicoCadastro medicoCadastro) {
        this.ativo = true;
        this.nome = medicoCadastro.nome();
        this.email = medicoCadastro.email();
        this.telefone = medicoCadastro.telefone();
        this.crm = medicoCadastro.crm();
        this.especialidade = medicoCadastro.especialidade();
        this.endereco = new Endereco(medicoCadastro.endereco());
    }

    public void atualizarInformacoes(@Valid MedicoUpdate medicoUpdate) {
        if (medicoUpdate.nome() != null){
            this.nome = medicoUpdate.nome();
        }
        if (medicoUpdate.telefone() != null){
        this.telefone = medicoUpdate.telefone();
        }
        if (medicoUpdate.endereco() != null){
            this.endereco.atualizarEndereco(medicoUpdate.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
