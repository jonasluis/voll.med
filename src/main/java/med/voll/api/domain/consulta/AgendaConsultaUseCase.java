package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.MedicoEntity;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultaUseCase {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void executar(@Valid AgendamentoConsultaDTO dto) {
        if (!pacienteRepository.existsById(dto.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
        if (dto.idMedico() != null && !medicoRepository.existsById(dto.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }

        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());
        var medico = escolherMedico(dto);
        var consulta = new ConsultaEntity(null, medico, paciente, dto.data());
        consultaRepository.save(consulta);
    }

    private MedicoEntity escolherMedico(@Valid AgendamentoConsultaDTO dto) {
        if (dto.idMedico() !=null){
            return medicoRepository.getReferenceById(dto.idMedico());
        }
        if (dto.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatoria quando o medico não for escolhido");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dto.especialidade(), dto.data());
    }
}
