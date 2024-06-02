package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.Demanda;
import net.josgonmor.ApiEventos.domain.Demandante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DemandaRepository extends JpaRepository<Demanda, Long> {
    Optional<Set<Demanda>> findByIdDemandante(Demandante demandante);
    @Query("SELECT o FROM Demanda o JOIN o.categorias c WHERE c.id IN :categoriaIds")
    List<Demanda> findByCategoriaIds(@Param("categoriaIds") Set<Long> categoriaIds);

    Optional<Demanda> findById(Long id);
    Optional<Set<Demanda>> findByIdDemandanteUsuarioUsuario(String usuario);
}