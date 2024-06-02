package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.Demandante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandanteRepository extends JpaRepository<Demandante, Long> {
    Demandante getDemandanteById(Long id);
}