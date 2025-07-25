package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedencia {

    public void validar(AgendamentoConsultaDTO dto){
        var dataConsulta = dto.data();
        var agora = LocalDateTime.now();

        var diferencaMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaMinutos < 30) {
            throw new ValidacaoException("`Consulta deve ser agenda com antecedencia minima de 30 minutos");
        }

    }
}
