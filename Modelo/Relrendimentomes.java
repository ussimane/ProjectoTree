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
@Table(name = "relrendimentomes", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relrendimentomes.findAll", query = "SELECT r FROM Relrendimentomes r")})
public class Relrendimentomes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "zeroquinhento")
    private Integer zeroquinhento;
    @Column(name = "quinhentotresmil")
    private Integer quinhentotresmil;
    @Column(name = "tresmilumquinhento")
    private Integer tresmilumquinhento;
    @Column(name = "cincomilumdezmil")
    private Integer cincomilumdezmil;
    @Column(name = "maisdedezmil")
    private Integer maisdedezmil;
    @Column(name = "total")
    private Integer total;

    public Relrendimentomes() {
    }

    public Relrendimentomes(String localidade) {
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

    public Integer getZeroquinhento() {
        return zeroquinhento;
    }

    public void setZeroquinhento(Integer zeroquinhento) {
        this.zeroquinhento = zeroquinhento;
    }

    public Integer getQuinhentotresmil() {
        return quinhentotresmil;
    }

    public void setQuinhentotresmil(Integer quinhentotresmil) {
        this.quinhentotresmil = quinhentotresmil;
    }

    public Integer getTresmilumquinhento() {
        return tresmilumquinhento;
    }

    public void setTresmilumquinhento(Integer tresmilumquinhento) {
        this.tresmilumquinhento = tresmilumquinhento;
    }

    public Integer getCincomilumdezmil() {
        return cincomilumdezmil;
    }

    public void setCincomilumdezmil(Integer cincomilumdezmil) {
        this.cincomilumdezmil = cincomilumdezmil;
    }

    public Integer getMaisdedezmil() {
        return maisdedezmil;
    }

    public void setMaisdedezmil(Integer maisdedezmil) {
        this.maisdedezmil = maisdedezmil;
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
        if (!(object instanceof Relrendimentomes)) {
            return false;
        }
        Relrendimentomes other = (Relrendimentomes) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relrendimentomes[ localidade=" + localidade + " ]";
    }
    
}
