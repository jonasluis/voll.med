package med.voll.voll.paciente.controller;

import jakarta.validation.Valid;
import med.voll.voll.paciente.dto.PacienteCadastro;
import med.voll.voll.paciente.dto.PacienteListagem;
import med.voll.voll.paciente.dto.PacienteUpdate;
import med.voll.voll.paciente.model.PacienteEntity;
import med.voll.voll.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteCadastro pacienteCadastro){
        pacienteRepository.save(new PacienteEntity(pacienteCadastro));
    }

    @GetMapping
    public Page<PacienteListagem> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return pacienteRepository.findAllByAtivoTrue(pageable).map(PacienteListagem::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid PacienteUpdate pacienteUpdate){
        var paciente = pacienteRepository.getReferenceById(pacienteUpdate.id());
        paciente.atualizarInformacoes(pacienteUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desativar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
    }

}
