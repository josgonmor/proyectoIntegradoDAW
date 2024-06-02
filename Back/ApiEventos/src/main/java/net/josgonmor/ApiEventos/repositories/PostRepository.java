package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.Post;
import net.josgonmor.ApiEventos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Set<Post>> findByIdUsuario(Usuario idUsuario);
}