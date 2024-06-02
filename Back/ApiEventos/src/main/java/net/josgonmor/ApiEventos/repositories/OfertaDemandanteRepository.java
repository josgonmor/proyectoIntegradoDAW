package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.OfertaDemandante;
import net.josgonmor.ApiEventos.domain.OfertaDemandanteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfertaDemandanteRepository extends JpaRepository<OfertaDemandante, OfertaDemandanteId> {
}