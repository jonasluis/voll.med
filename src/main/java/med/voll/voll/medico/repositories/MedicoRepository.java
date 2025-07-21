package med.voll.voll.medico.repositories;

import med.voll.voll.medico.model.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
}
