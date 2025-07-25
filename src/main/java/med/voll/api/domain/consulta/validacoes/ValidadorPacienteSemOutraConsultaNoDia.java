package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.domain.consulta.ConsultaRepository;

public class ValidadorPacienteSemOutraConsultaNoDia {

    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO dto){
        var primeiroHorario = dto.data().withHour(7);
        var ultimoHorario = dto.data().withHour(18);


        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dto.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente ja possui outra consulta agendada nesse mesmo dia");
        }
    }
}
