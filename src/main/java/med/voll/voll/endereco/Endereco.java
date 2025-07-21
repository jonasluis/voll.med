package med.voll.voll.endereco;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Endereco {
    @NotBlank
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.logradouro();
        this.numero = enderecoDTO.numero();
        this.complemento = enderecoDTO.complemento();
        this.bairro = enderecoDTO.bairro();
        this.cidade = enderecoDTO.cidade();
        this.uf = enderecoDTO.uf();
        this.cep = enderecoDTO.cep();
    }
}
