package med.voll.voll.medico.controller;

import jakarta.validation.Valid;
import med.voll.voll.medico.dto.MedicoCadastro;
import med.voll.voll.medico.dto.MedicoListagem;
import med.voll.voll.medico.dto.MedicoUpdate;
import med.voll.voll.medico.model.MedicoEntity;
import med.voll.voll.medico.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoCadastro medicoCadastro){
        medicoRepository.save(new MedicoEntity(medicoCadastro));
    }

    @GetMapping
    public Page<MedicoListagem> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return medicoRepository.findAllByAtivoTrue(pageable).map(MedicoListagem::new);
    }

    @PutMapping()
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoUpdate medicoUpdate) {
        var medico = medicoRepository.getReferenceById(medicoUpdate.id());
        medico.atualizarInformacoes(medicoUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desativar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }

}
