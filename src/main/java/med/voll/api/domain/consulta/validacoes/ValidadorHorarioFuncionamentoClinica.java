package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas{

    public void validar(AgendamentoConsultaDTO dto){
        var dataConsulta = dto.data();
        var domingo  = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDeFecharClinica = dataConsulta.getHour() >= 18;

        if (domingo || antesDaAberturaDaClinica || depoisDeFecharClinica){
            throw new ValidacaoException("Consulta fora de horario de funcionamento da clinica");
        }
    }
}
