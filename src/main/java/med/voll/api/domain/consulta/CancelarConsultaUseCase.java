package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelarConsultaUseCase {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void cancelar(CancelamentoConsultaDTO dto) {
        if (!consultaRepository.existsById(dto.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dto.idConsulta());
        consulta.cancelar(dto.motivo());
    }
}
