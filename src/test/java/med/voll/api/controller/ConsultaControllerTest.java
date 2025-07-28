package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaConsultaUseCase;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.domain.consulta.AgendamentoConsultaResponse;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AgendamentoConsultaDTO> agendamentoConsultaJson;

    @Autowired
    private JacksonTester<AgendamentoConsultaResponse> detalhamentoConsultaJson;

    @MockBean
    private AgendaConsultaUseCase agendaConsulta;

    @Test
    @DisplayName("Deve devolver codico http 400 quando informacoes estao invalidos")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver codico http 200 quando informacoes estao validos")
    @WithMockUser
    void agendar_cenario2() throws Exception {

        var data = LocalDateTime.now().plusHours(1) ;
        var especialidade = Especialidade.DERMATOLOGISTA;

        var detalhamentoAgendamento = new AgendamentoConsultaResponse(null, 21l, 23l, data);

        when(agendaConsulta.executar(any())).thenReturn( detalhamentoAgendamento);

        var response = mvc.perform(
                post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agendamentoConsultaJson.write(
                                new AgendamentoConsultaDTO(21l, 23l, data, especialidade)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonEsperado = detalhamentoConsultaJson.write(
                detalhamentoAgendamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}