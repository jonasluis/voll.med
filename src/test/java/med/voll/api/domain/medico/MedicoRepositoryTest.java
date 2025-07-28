package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.ConsultaEntity;
import med.voll.api.domain.consulta.MotivoCancelamento;
import med.voll.api.domain.endereco.EnderecoDTO;
import med.voll.api.domain.paciente.PacienteCadastro;
import med.voll.api.domain.paciente.PacienteEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deve devolver null quando oo medico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGISTA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

       var medicoLivre =  medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGISTA, proximaSegundaAs10);
       assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deve devolver medico quando o medico cadastrado esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGISTA);

       var medicoLivre =  medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGISTA, proximaSegundaAs10);
       assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(MedicoEntity medico, PacienteEntity paciente, LocalDateTime data) {
        em.persist(new ConsultaEntity(null, medico, paciente, data));
    }

    private MedicoEntity cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new MedicoEntity(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private PacienteEntity cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new PacienteEntity(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private MedicoCadastro dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new MedicoCadastro(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private PacienteCadastro dadosPaciente(String nome, String email, String cpf) {
        return new PacienteCadastro(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}