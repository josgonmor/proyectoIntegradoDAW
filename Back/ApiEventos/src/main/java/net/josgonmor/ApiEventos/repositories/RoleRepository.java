package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNombre(String nombre);
}