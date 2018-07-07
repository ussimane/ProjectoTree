/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ussimane
 */
@Entity
@Table(name = "relescalao", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relescalao.findAll", query = "SELECT r FROM Relescalao r")})
public class Relescalao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "zeroum")
    private Integer zeroum;
    @Column(name = "umcinco")
    private Integer umcinco;
    @Column(name = "maiscinco")
    private Integer maiscinco;
    @Column(name = "total")
    private Integer total;

    public Relescalao() {
    }

    public Relescalao(String localidade) {
        this.localidade = localidade;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Integer getZeroum() {
        return zeroum;
    }

    public void setZeroum(Integer zeroum) {
        this.zeroum = zeroum;
    }

    public Integer getUmcinco() {
        return umcinco;
    }

    public void setUmcinco(Integer umcinco) {
        this.umcinco = umcinco;
    }

    public Integer getMaiscinco() {
        return maiscinco;
    }

    public void setMaiscinco(Integer maiscinco) {
        this.maiscinco = maiscinco;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localidade != null ? localidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relescalao)) {
            return false;
        }
        Relescalao other = (Relescalao) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relescalao[ localidade=" + localidade + " ]";
    }
    
}
