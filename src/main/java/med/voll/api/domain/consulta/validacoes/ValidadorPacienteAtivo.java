package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;

import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas{
    @Autowired
    private PacienteRepository repository;

    public void validar(AgendamentoConsultaDTO dto){
        if (dto.idPaciente() == null){
            return;
        }

        var pacienteAtivo = repository.findAtivoByID(dto.idPaciente());

        if (!pacienteAtivo) {
            throw new ValidacaoException("Consulta nao pode ser agendada com paciente inativo!");
        }
    }
}
