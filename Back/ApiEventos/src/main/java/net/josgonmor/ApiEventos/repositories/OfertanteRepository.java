package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.Competencia;
import net.josgonmor.ApiEventos.domain.Ofertante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertanteRepository extends JpaRepository<Ofertante, Long> {
    Ofertante findOfertanteById(Long id);
}