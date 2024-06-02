package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {
    @Query("SELECT c FROM Competencia c JOIN c.competenciaOfertantes o WHERE o.id.idOfertante = :ofertanteId")
    List<Competencia> findByOfertanteId(@Param("ofertanteId") Long ofertanteId);

}