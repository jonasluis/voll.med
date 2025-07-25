package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas{
    @Autowired
    private MedicoRepository repository;

    public void validar(AgendamentoConsultaDTO dto){
        if (dto.idMedico() == null){
            return;
        }

        var medicoAtivo = repository.findAtivoByID(dto.idMedico());

        if (!medicoAtivo) {
            throw new ValidacaoException("Consulta nao pode ser agendada com medico inativo!");
        }
    }
}
