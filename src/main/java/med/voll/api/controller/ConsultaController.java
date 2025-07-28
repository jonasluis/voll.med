package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "jwt_auth")
public class ConsultaController {

    @Autowired
    private AgendaConsultaUseCase agendaConsulta;

    @Autowired
    private CancelarConsultaUseCase cancelarConsulta;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDTO dto){
        var agendamento = agendaConsulta.executar(dto);

        return ResponseEntity.ok(agendamento);
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsultaDTO dto) {
        cancelarConsulta.cancelar(dto);
        return ResponseEntity.noContent().build();
    }
}
