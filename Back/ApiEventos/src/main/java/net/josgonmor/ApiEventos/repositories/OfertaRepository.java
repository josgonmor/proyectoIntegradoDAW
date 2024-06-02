package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.Categoria;
import net.josgonmor.ApiEventos.domain.Oferta;
import net.josgonmor.ApiEventos.domain.Ofertante;
import net.josgonmor.ApiEventos.dto.CategoriaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    Set<Oferta> findByIdOfertante(Ofertante idOfertante);
    List<Oferta> findByCategorias(Set<Categoria> categorias);

    @Query("SELECT o FROM Oferta o JOIN o.categorias c WHERE c.id IN :categoriaIds")
    List<Oferta> findByCategoriaIds(@Param("categoriaIds") Set<Long> categoriaIds);

    Optional<Oferta> findById(Long id);

    Optional<List<Oferta>> findByIdOfertanteUsuarioUsuario(String usuario);
    @Query("SELECT o FROM Oferta o LEFT JOIN FETCH o.categorias LEFT JOIN o.idOfertante WHERE o.id = :id")
    Oferta findByIdCompleto(@Param("id") Long id);

    @Query("Select o FROM Oferta o LEFT join fetch o.ofertaDemandantes d where d.id.idDemandante = :id")
    Optional<Set<Oferta>> findByIdDemandate(@Param("id") Long id);
}