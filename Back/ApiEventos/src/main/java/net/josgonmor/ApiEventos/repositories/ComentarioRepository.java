package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}