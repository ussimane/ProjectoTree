/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author MASSINGUE
 */
@Embeddable
public class EquipamentofaltaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idoficina", nullable = false)
    private int idoficina;
    @Basic(optional = false)
    @Column(name = "idequipamento", nullable = false)
    private int idequipamento;

    public EquipamentofaltaPK() {
    }

    public EquipamentofaltaPK(int idoficina, int idequipamento) {
        this.idoficina = idoficina;
        this.idequipamento = idequipamento;
    }

    public int getIdoficina() {
        return idoficina;
    }

    public void setIdoficina(int idoficina) {
        this.idoficina = idoficina;
    }

    public int getIdequipamento() {
        return idequipamento;
    }

    public void setIdequipamento(int idequipamento) {
        this.idequipamento = idequipamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idoficina;
        hash += (int) idequipamento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipamentofaltaPK)) {
            return false;
        }
        EquipamentofaltaPK other = (EquipamentofaltaPK) object;
        if (this.idoficina != other.idoficina) {
            return false;
        }
        if (this.idequipamento != other.idequipamento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.EquipamentofaltaPK[ idoficina=" + idoficina + ", idequipamento=" + idequipamento + " ]";
    }
    
}
