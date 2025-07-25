package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.domain.consulta.ConsultaRepository;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {

    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO dto){
        var medicoTemOutraConsutaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dto.idMedico(), dto.data());
        if (medicoTemOutraConsutaNoMesmoHorario){
            throw new ValidacaoException("Medico ja possui outra consulta agendada nesse mesmo horario");
        }
    }
}
