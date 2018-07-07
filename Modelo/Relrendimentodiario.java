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
@Table(name = "relrendimentodiario", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relrendimentodiario.findAll", query = "SELECT r FROM Relrendimentodiario r")})
public class Relrendimentodiario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "zerocem")
    private Integer zerocem;
    @Column(name = "centoumtrezento")
    private Integer centoumtrezento;
    @Column(name = "trezentoumquinhento")
    private Integer trezentoumquinhento;
    @Column(name = "quinhentoummil")
    private Integer quinhentoummil;
    @Column(name = "maisdemil")
    private Integer maisdemil;
    @Column(name = "total")
    private Integer total;

    public Relrendimentodiario() {
    }

    public Relrendimentodiario(String localidade) {
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

    public Integer getZerocem() {
        return zerocem;
    }

    public void setZerocem(Integer zerocem) {
        this.zerocem = zerocem;
    }

    public Integer getCentoumtrezento() {
        return centoumtrezento;
    }

    public void setCentoumtrezento(Integer centoumtrezento) {
        this.centoumtrezento = centoumtrezento;
    }

    public Integer getTrezentoumquinhento() {
        return trezentoumquinhento;
    }

    public void setTrezentoumquinhento(Integer trezentoumquinhento) {
        this.trezentoumquinhento = trezentoumquinhento;
    }

    public Integer getQuinhentoummil() {
        return quinhentoummil;
    }

    public void setQuinhentoummil(Integer quinhentoummil) {
        this.quinhentoummil = quinhentoummil;
    }

    public Integer getMaisdemil() {
        return maisdemil;
    }

    public void setMaisdemil(Integer maisdemil) {
        this.maisdemil = maisdemil;
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
        if (!(object instanceof Relrendimentodiario)) {
            return false;
        }
        Relrendimentodiario other = (Relrendimentodiario) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relrendimentodiario[ localidade=" + localidade + " ]";
    }
    
}
