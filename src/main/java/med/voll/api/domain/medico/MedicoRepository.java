package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    Page<MedicoEntity> findAllByAtivoTrue(Pageable pageable);

    @Query(
            """
                select m from MedicoEntity m
                    where
                    m.ativo = true
                    and
                    m.especialidade = :especialidade
                    and
                    m.id not in(
                       select c.medico.id from ConsultaEntity c
                       where
                       c.data = :data
                   )
                   order by rand()
                   limit 1
            """)
    MedicoEntity escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
