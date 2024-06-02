package net.josgonmor.ApiEventos.repositories;

import net.josgonmor.ApiEventos.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}