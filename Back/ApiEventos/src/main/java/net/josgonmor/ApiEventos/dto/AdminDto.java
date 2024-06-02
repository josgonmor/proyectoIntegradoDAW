package net.josgonmor.ApiEventos.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link net.josgonmor.ApiEventos.domain.Admin}
 */
@Value
public class AdminDto implements Serializable {
    Long id;
}