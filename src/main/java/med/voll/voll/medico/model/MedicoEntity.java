package med.voll.voll.medico.model;

import lombok.*;
import med.voll.voll.endereco.model.Endereco;
import med.voll.voll.medico.model.enums.Especialidade;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicoEntity {
    String nome;
    String email;
    Integer telefone;
    Integer CRM;
    Especialidade especialidade;
    Endereco enderecoCompleto;
}
