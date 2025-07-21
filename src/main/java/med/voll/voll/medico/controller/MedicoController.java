package med.voll.voll.medico.controller;

import jakarta.validation.Valid;
import med.voll.voll.medico.dto.MedicoRequest;
import med.voll.voll.medico.model.MedicoEntity;
import med.voll.voll.medico.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
