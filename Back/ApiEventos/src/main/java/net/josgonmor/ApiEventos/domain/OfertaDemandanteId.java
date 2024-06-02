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
public class OfertaDemandanteId implements Serializable {
    private static final long serialVersionUID = -6303634141497758803L;
    @Column(name = "id_oferta", nullable = false)
    private Long idOferta;

    @Column(name = "id_demandante", nullable = false)
    private Long idDemandante;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OfertaDemandanteId entity = (OfertaDemandanteId) o;
        return Objects.equals(this.idDemandante, entity.idDemandante) &&
                Objects.equals(this.idOferta, entity.idOferta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDemandante, idOferta);
    }

}