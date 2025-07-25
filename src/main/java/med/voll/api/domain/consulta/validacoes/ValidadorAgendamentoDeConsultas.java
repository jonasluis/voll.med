package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsultas {
    void validar(AgendamentoConsultaDTO dto);
}
