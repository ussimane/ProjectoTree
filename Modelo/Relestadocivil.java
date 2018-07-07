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
@Table(name = "relestadocivil", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relestadocivil.findAll", query = "SELECT r FROM Relestadocivil r")})
public class Relestadocivil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "tcasado")
    private Integer tcasado;
    @Column(name = "tsolteiro")
    private Integer tsolteiro;
    @Column(name = "tviuvo")
    private Integer tviuvo;
    @Column(name = "tdivorciado")
    private Integer tdivorciado;
    @Column(name = "toutro")
    private Integer toutro;
    @Column(name = "total")
    private Integer total;

    public Relestadocivil() {
    }

    public Relestadocivil(String localidade) {
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

    public Integer getTcasado() {
        return tcasado;
    }

    public void setTcasado(Integer tcasado) {
        this.tcasado = tcasado;
    }

    public Integer getTsolteiro() {
        return tsolteiro;
    }

    public void setTsolteiro(Integer tsolteiro) {
        this.tsolteiro = tsolteiro;
    }

    public Integer getTviuvo() {
        return tviuvo;
    }

    public void setTviuvo(Integer tviuvo) {
        this.tviuvo = tviuvo;
    }

    public Integer getTdivorciado() {
        return tdivorciado;
    }

    public void setTdivorciado(Integer tdivorciado) {
        this.tdivorciado = tdivorciado;
    }

    public Integer getToutro() {
        return toutro;
    }

    public void setToutro(Integer toutro) {
        this.toutro = toutro;
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
        if (!(object instanceof Relestadocivil)) {
            return false;
        }
        Relestadocivil other = (Relestadocivil) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relestadocivil[ localidade=" + localidade + " ]";
    }
    
}
