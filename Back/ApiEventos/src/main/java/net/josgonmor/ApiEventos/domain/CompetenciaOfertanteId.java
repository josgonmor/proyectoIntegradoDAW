package net.josgonmor.ApiEventos.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CompetenciaOfertanteId implements Serializable {
    private static final long serialVersionUID = -5793143798639566122L;
    @Column(name = "id_competencia", nullable = false)
    private Long idCompetencia;

    @Column(name = "id_ofertante", nullable = false)
    private Long idOfertante;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CompetenciaOfertanteId entity = (CompetenciaOfertanteId) o;
        return Objects.equals(this.idOfertante, entity.idOfertante) &&
                Objects.equals(this.idCompetencia, entity.idCompetencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfertante, idCompetencia);
    }

}