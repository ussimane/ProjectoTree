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
public class ArtigofinalPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idformador", nullable = false)
    private int idformador;
    @Basic(optional = false)
    @Column(name = "idproduto", nullable = false)
    private int idproduto;

    public ArtigofinalPK() {
    }

    public ArtigofinalPK(int idformador, int idproduto) {
        this.idformador = idformador;
        this.idproduto = idproduto;
    }

    public int getIdformador() {
        return idformador;
    }

    public void setIdformador(int idformador) {
        this.idformador = idformador;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idformador;
        hash += (int) idproduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtigofinalPK)) {
            return false;
        }
        ArtigofinalPK other = (ArtigofinalPK) object;
        if (this.idformador != other.idformador) {
            return false;
        }
        if (this.idproduto != other.idproduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ArtigofinalPK[ idformador=" + idformador + ", idproduto=" + idproduto + " ]";
    }
    
}
