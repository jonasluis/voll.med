package med.voll.voll.medico.controller;

import jakarta.validation.Valid;
import med.voll.voll.medico.dto.MedicoRequest;
import med.voll.voll.medico.dto.MedicoResponse;
import med.voll.voll.medico.model.MedicoEntity;
import med.voll.voll.medico.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoRequest medicoRequest){
        medicoRepository.save(new MedicoEntity(medicoRequest));
    }

    @GetMapping
    public Page<MedicoResponse> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return medicoRepository.findAll(pageable).map(MedicoResponse::new);
    }
}
