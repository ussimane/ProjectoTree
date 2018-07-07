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
public class ArtigoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idformando", nullable = false)
    private int idformando;
    @Basic(optional = false)
    @Column(name = "idproduto", nullable = false)
    private int idproduto;

    public ArtigoPK() {
    }

    public ArtigoPK(int idformando, int idproduto) {
        this.idformando = idformando;
        this.idproduto = idproduto;
    }

    public int getIdformando() {
        return idformando;
    }

    public void setIdformando(int idformando) {
        this.idformando = idformando;
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
        hash += (int) idformando;
        hash += (int) idproduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtigoPK)) {
            return false;
        }
        ArtigoPK other = (ArtigoPK) object;
        if (this.idformando != other.idformando) {
            return false;
        }
        if (this.idproduto != other.idproduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ArtigoPK[ idformando=" + idformando + ", idproduto=" + idproduto + " ]";
    }
    
}
