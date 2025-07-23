package med.voll.voll.paciente.controller;

import jakarta.validation.Valid;
import med.voll.voll.paciente.dto.PacienteCadastro;
import med.voll.voll.paciente.dto.PacienteListagem;
import med.voll.voll.paciente.dto.PacienteResponse;
import med.voll.voll.paciente.dto.PacienteUpdate;
import med.voll.voll.paciente.model.PacienteEntity;
import med.voll.voll.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteCadastro pacienteCadastro, UriComponentsBuilder uriBuilder){
        var paciente = new PacienteEntity(pacienteCadastro);
        pacienteRepository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return  ResponseEntity.created(uri).body(new PacienteResponse(paciente));

    }

    @GetMapping
    public ResponseEntity<Page<PacienteListagem>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
         var page = pacienteRepository.findAllByAtivoTrue(pageable).map(PacienteListagem::new);
         return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid PacienteUpdate pacienteUpdate){
        var paciente = pacienteRepository.getReferenceById(pacienteUpdate.id());
        paciente.atualizarInformacoes(pacienteUpdate);
        return ResponseEntity.ok(new PacienteResponse(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteResponse(paciente));
    }

}
