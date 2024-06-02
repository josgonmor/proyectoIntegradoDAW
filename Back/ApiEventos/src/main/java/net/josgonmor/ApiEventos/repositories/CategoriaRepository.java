package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("SELECT c FROM Categoria c JOIN c.ofertas o WHERE o.id = :ofertaId")
    List<Categoria> findByOfertaId(@Param("ofertaId") Long ofertaId);
}