package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "pacientes")
@EqualsAndHashCode(of = "id")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public PacienteEntity(@Valid PacienteCadastro pacienteCadastro) {
        this.ativo = true;
        this.nome = pacienteCadastro.nome();
        this.email = pacienteCadastro.email();
        this.telefone = pacienteCadastro.telefone();
        this.cpf = pacienteCadastro.cpf();
        this.endereco = new Endereco(pacienteCadastro.endereco());
    }

    public void atualizarInformacoes(@Valid PacienteUpdate pacienteUpdate) {
        if (pacienteUpdate.nome() != null){
            this.nome = pacienteUpdate.nome();
        }
        if (pacienteUpdate.telefone() != null){
            this.telefone = pacienteUpdate.telefone();
        }
        if (pacienteUpdate.endereco() != null){
            this.endereco.atualizarEndereco(pacienteUpdate.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
