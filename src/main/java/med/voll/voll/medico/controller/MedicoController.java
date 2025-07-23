package med.voll.voll.medico.controller;

import jakarta.validation.Valid;
import med.voll.voll.medico.dto.MedicoCadastro;
import med.voll.voll.medico.dto.MedicoListagem;
import med.voll.voll.medico.dto.MedicoUpdate;
import med.voll.voll.medico.dto.MedicoResponse;
import med.voll.voll.medico.model.MedicoEntity;
import med.voll.voll.medico.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoCadastro medicoCadastro, UriComponentsBuilder uriBuilder){
        var medico = new MedicoEntity(medicoCadastro);
        medicoRepository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoResponse(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListagem>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        var page = medicoRepository.findAllByAtivoTrue(pageable).map(MedicoListagem::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid MedicoUpdate medicoUpdate) {
        var medico = medicoRepository.getReferenceById(medicoUpdate.id());
        medico.atualizarInformacoes(medicoUpdate);
        return ResponseEntity.ok(new MedicoResponse(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new MedicoResponse(medico));
    }

}
